
package com.hx.base.cache;

public abstract class WashoutExecutorFactory {
	
	protected static WashoutExecutor fifo;
	
	protected static WashoutExecutor filo;
	
	protected static WashoutExecutor lfu;
	
	protected static WashoutExecutor lru;
	
	public static synchronized WashoutExecutor createWashoutExecutor(WashoutStrategy washoutStrategy) {
		if (washoutStrategy == WashoutStrategy.FILO) {
			if (filo == null) {
				filo = new FiloWashoutExecutor();
			}
			return fifo;
		} else if (washoutStrategy == WashoutStrategy.LFU) {
			if (lfu == null) {
				lfu = new LfuWashoutExecutor();
			}
			return lfu;
		} else if (washoutStrategy == WashoutStrategy.LRU) {
			if (lru == null) {
				lru = new LruWashoutExecutor();
			}
			return lru;
		} else {
			if (fifo == null) {
				fifo = new FifoWashoutExecutor();
			}
			return fifo;
		}
	}
}
