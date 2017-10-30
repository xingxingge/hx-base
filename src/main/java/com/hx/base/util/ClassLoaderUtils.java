/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hx.base.util;

public abstract class ClassLoaderUtils {
  public static ClassLoader getDefaultClassLoader() {
    ClassLoader cl = null;
    try {
      cl = Thread.currentThread().getContextClassLoader();
    } catch (Throwable ex) {
      // Cannot access thread context ClassLoader - falling back to system class
      // loader... class path
    }
    if (cl == null) {
      // No thread context class loader -> use class loader of this class.
      cl = ClassLoaderUtils.class.getClassLoader();
    }
    return cl;
  }

  public static ClassLoader overrideThreadContextClassLoader(
          ClassLoader classLoaderToUse) {
    Thread currentThread = Thread.currentThread();
    ClassLoader threadContextClassLoader = currentThread
            .getContextClassLoader();
    if (classLoaderToUse != null
            && !classLoaderToUse.equals(threadContextClassLoader)) {
      currentThread.setContextClassLoader(classLoaderToUse);
      return threadContextClassLoader;
    } else {
      return null;
    }
  }
}
