
package com.hx.base.cache;



import java.util.Collection;
import java.util.Iterator;


//Least Recently Used
public class LruWashoutExecutor implements WashoutExecutor {

	@Override
	public CacheElement washout(Collection<CacheElement> collection) {
		// TODO Auto-generated method stub
		CacheElement lru = null;
        synchronized(collection) {
            lru = lru(collection);
            collection.remove(lru);
        }
        return lru;
	}

    private CacheElement lru(Collection<CacheElement> collection) {
        CacheElement lru = null;
        long accessTime = 0;
        for(Iterator<CacheElement> it = collection.iterator(); it.hasNext();) {
            CacheElement element = (CacheElement)it.next();
            if (element.getLastAccessTime() < accessTime || accessTime == 0) {
                accessTime = element.getLastAccessTime();
                lru = element;
            }
        }
        return lru;
    }
}
