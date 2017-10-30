
package com.hx.base.cache;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 
 * @author
 *
 * @param <K>
 * @param <V>
 */
public class CacheImpl<K, V> implements Cache<K, V>{
	
	private static final Logger logger = LoggerFactory.getLogger(CacheImpl.class);
	
	protected List<CacheElement> listCache;
	
	protected Map<K,CacheElement> mapCache;
	
	protected CacheMetadata cacheMetadata;
	
	protected WashoutExecutor washoutExecutor;
	
	public CacheImpl(CacheMetadata cacheMetadata) {
		Validate.notNull(cacheMetadata, "Parameter 'cacheMetadata' is null!");
		this.cacheMetadata = cacheMetadata;
		washoutExecutor = WashoutExecutorFactory.createWashoutExecutor(cacheMetadata.getWashoutStrategy());
		initialize();
	}
	
	protected void initialize() {
		listCache = new LinkedList<>();
		if (cacheMetadata.getSize() != CacheMetadata.INFINITE)
			mapCache = new HashMap<>(cacheMetadata.getSize());
		else
			mapCache = new HashMap<>();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public synchronized V get(Object key) {
		CacheElement element = mapCache.get(key);
		if (element == null)
			return null;
		element.hit();
		return (V)element.getValue();
	}

	@Override
	public CacheMetadata getCacheMetadata() {
		return cacheMetadata;
	}

	@Override
	public WashoutExecutor getWashoutExecutor() {
		return washoutExecutor;
	}

	@Override
	public synchronized boolean isFull() {
		if (cacheMetadata.getSize() == CacheMetadata.INFINITE)
            return false;
        if (listCache.size() < cacheMetadata.getSize())
            return false;
        return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized V put(K key, V value) {
        CacheElement oldElement = null;
        if (isFull()) {
            oldElement = washoutExecutor.washout(listCache);
            if (oldElement == null)
                return value;
            mapCache.remove(oldElement.getKey());
            if (logger.isDebugEnabled()) {
            	logger.debug("One cache element is washed out!");
            }
        }   
        CacheElement element = new CacheElementImpl(key);
        element.setValue(value);
        listCache.add(element);
        mapCache.put(key, element);
        if (oldElement != null)
        	return (V)oldElement.getValue();
        return null;
	}

	@Override
	public int getSize() {
		return listCache.size();
	}
	
	public void clear() {
		initialize();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<V> values() {
		List<V> values = new ArrayList<V>(listCache.size());
		for(CacheElement cacheElement : listCache) {
			values.add((V)cacheElement.getValue());
		}
		return values;
	}

	@Override
	public List<Object[]> values(Cache.RecordConverter<V> converter) {
		List<Object[]> values = new ArrayList<Object[]>(listCache.size());
		for(CacheElement cacheElement : listCache) {
			V value = (V)cacheElement.getValue();
			values.add(converter.convert(value));
		}
		return values;
	}

}
