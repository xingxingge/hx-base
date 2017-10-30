package com.hx.base.cache;


import java.util.List;

/**
 *
 * @author Tang Tadin
 *
 */
public interface Cache<K, V> {
	
	 interface RecordConverter<V> {
		Object[] convert(V value);
	}

	CacheMetadata getCacheMetadata();
	
	V get(Object key);
	
	V put(K key, V value);
	
	boolean isFull();
	
	int getSize();
	
	WashoutExecutor getWashoutExecutor();
	
	void clear();
	
	List<V> values();
	
	List<Object[]> values(RecordConverter<V> converter);
}
