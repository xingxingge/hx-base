/*
  Copyright 2012 Tang Tadin.

  This file should be part of the source code distribution of "Memory object query language"
  (the Program): see the accompanying README files for more info.

  This Program is free software; you can redistribute it and/or modify it under the terms
  of the GNU Lesser General Public License as published by the Free Software Foundation;
  either version 3 of the License, or (at your option) any later version.

  This Program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY,
  either expressed or implied; without even the implied warranty of MERCHANTABILITY or
  FITNESS FOR A PARTICULAR PURPOSE. See the License for more details.

  You should have received a copy of the GNU Lesser General Public License along with this
  Program (see README files); if not, go to the GNU website (http://www.gnu.org/licenses/).

  Redistribution and use, with or without modification, are permitted provided that such
  redistributions retain the above copyright notice, license and disclaimer, along with
  this list of conditions.
*/
package com.hx.base.cache;

import org.apache.commons.lang.Validate;

import java.io.Serializable;


public class CacheMetadata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int INFINITE = 0;
	
	public static final int DEFAULT_CACHE_SIZE = INFINITE;
	
	public static final CacheMetadata DEFAULT_CACHE = new CacheMetadata(INFINITE);
	
	protected int size;
	
	protected WashoutStrategy washoutStrategy = WashoutStrategy.FIFO;
	
	public CacheMetadata(int size) {
		Validate.isTrue(size > -1, "Parameter 'size' less than 0!");
		this.size = size;
	}
	
	public CacheMetadata(int size, WashoutStrategy washoutStrategy) {
		Validate.isTrue(size > -1, "Parameter 'size' less than 0!");
		Validate.notNull(washoutStrategy, "Parameter 'washoutStrategy' is null!");
		this.size = size;
		this.washoutStrategy = washoutStrategy;
	}

	public int getSize() {
		return size;
	}

	public WashoutStrategy getWashoutStrategy() {
		return washoutStrategy;
	}

	public void setWashoutStrategy(WashoutStrategy washoutStrategy) {
		Validate.notNull(washoutStrategy, "Parameter 'washoutStrategy' is null!");
		this.washoutStrategy = washoutStrategy;
	}

}
