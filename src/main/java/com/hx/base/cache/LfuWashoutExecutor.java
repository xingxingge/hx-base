
package com.hx.base.cache;



import java.util.Collection;
import java.util.Iterator;


//Least Frequently Used 
public class LfuWashoutExecutor implements WashoutExecutor {

	@Override
	public CacheElement washout(Collection<CacheElement> collection) {
		// TODO Auto-generated method stub
		CacheElement lfu = null;
        synchronized(collection) {
            lfu = lfu(collection);
            collection.remove(lfu);
        }
        return lfu;
	}

    private CacheElement lfu(Collection<CacheElement> collection) {
        CacheElement lfu = null;
        long accessTimes = 0;
        for(Iterator<CacheElement> it = collection.iterator(); it.hasNext();) {
            CacheElement element = it.next();
            if (element.getAccessTimes() < accessTimes || accessTimes == 0) {
                accessTimes = element.getAccessTimes();
                lfu = element;
            }
        }
        return lfu;
    }
}
