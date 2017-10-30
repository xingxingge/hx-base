
package com.hx.base.cache;

import org.apache.commons.lang.Validate;

public class MapEntryImpl<K, V> implements MapEntry<K, V> {
	
	protected K key;
	
	protected V value;
	
	public MapEntryImpl(K key) {
		Validate.notNull(key, "Parameter 'key' is empty!");
		this.key = key;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}
}
