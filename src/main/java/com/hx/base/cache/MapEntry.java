
package com.hx.base.cache;

public interface MapEntry<K,V> {
	
	K getKey();
	
	V getValue();
	
	V setValue(V value);
}
