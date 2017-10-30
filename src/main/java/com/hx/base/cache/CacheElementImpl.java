
package com.hx.base.cache;


import java.io.Serializable;


public class CacheElementImpl extends MapEntryImpl<Object, Object> implements CacheElement, Serializable {


  private static final long serialVersionUID = 1L;

  protected long lastAccessTime;
  protected long accessTimes;

  public CacheElementImpl(Object key) {
    super(key);
    hit();
  }

  public void hit() {
    lastAccessTime = System.currentTimeMillis();
    accessTimes++;
  }

  /**
   * @return the lastAccessTime
   */
  public long getLastAccessTime() {
    return lastAccessTime;
  }

  /**
   * @return the accessTimes
   */
  public long getAccessTimes() {
    return accessTimes;
  }
}
