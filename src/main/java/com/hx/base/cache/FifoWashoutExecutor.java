
package com.hx.base.cache;

import java.util.Collection;
import java.util.Iterator;

public class FifoWashoutExecutor implements WashoutExecutor {

	@Override
	public CacheElement washout(Collection<CacheElement> collection) {
		// TODO Auto-generated method stub
		CacheElement element = null;
        synchronized(collection) {
            Iterator<CacheElement> it = collection.iterator();
            if (it.hasNext()) {
                element = it.next();
                it.remove();
            }
        }
        return element;
	}

}
