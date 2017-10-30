package com.hx.base.cache;

import junit.framework.TestCase;

/**
 * Created by hx on 16-11-27.
 */
public class TestCache extends TestCase {
  public void testLRU1(){
    CacheMetadata cacheMetadata = new CacheMetadata(5,WashoutStrategy.LRU);
    Cache<Integer,Integer> cache = new CacheImpl<Integer, Integer>(cacheMetadata);
    cache.put(1,1);
    cache.put(2,2);
    cache.put(3,3);
    cache.put(4,4);
    cache.put(5,5);
    System.out.println(cache.get(1));
    cache.put(6,6);
  }
  public void testLFU1(){
    CacheMetadata cacheMetadata = new CacheMetadata(5,WashoutStrategy.LFU);
    Cache<Integer,Integer> cache = new CacheImpl<Integer, Integer>(cacheMetadata);
    cache.put(1,1);
    cache.put(2,2);
    cache.put(3,3);
    cache.put(4,4);
    cache.put(5,5);
    System.out.println(cache.get(1));
    cache.put(6,6);
  }
  public void testFIFO1(){
    CacheMetadata cacheMetadata = new CacheMetadata(5,WashoutStrategy.FIFO);
    Cache<Integer,Integer> cache = new CacheImpl<Integer, Integer>(cacheMetadata);
    cache.put(1,1);
    cache.put(2,2);
    cache.put(3,3);
    cache.put(4,4);
    cache.put(5,5);
    System.out.println(cache.get(1));
    cache.put(6,6);
  }

}
