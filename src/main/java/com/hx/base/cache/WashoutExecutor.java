
package com.hx.base.cache;

import java.util.Collection;


public interface WashoutExecutor {

	CacheElement washout(Collection<CacheElement> collection);
}
