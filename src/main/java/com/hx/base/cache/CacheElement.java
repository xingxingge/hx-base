
package com.hx.base.cache;


public interface CacheElement extends MapEntry<Object, Object> {

  void hit();

  /**
   * @return the lastAccessTime
   */
  long getLastAccessTime();

  /**
   * @return the accessTimes
   */
  long getAccessTimes();
}
