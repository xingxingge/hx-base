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

package com.hx.base.io;


import com.hx.base.util.ClassLoaderUtils;
import com.hx.base.util.PathUtils;
import com.hx.base.util.ResourceUtils;
import org.apache.commons.lang.ObjectUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ClassPathResource extends AbstractResource {

  private final String path;

  private ClassLoader classLoader;
  @SuppressWarnings("unchecked")
  private Class clazz;

  /**
   * Create a new ClassPathResource for ClassLoader usage. A leading slash will
   * be removed, as the ClassLoader resource access methods will not accept it.
   * <p>
   * The thread context class loader will be used for loading the resource.
   * 
   * @param path
   *          the absolute path within the class path
   * @see ClassLoader#getResourceAsStream(String)
   */
  public ClassPathResource(String path) {
    this(path, (ClassLoader) null);
  }

  /**
   * Create a new ClassPathResource for ClassLoader usage. A leading slash will
   * be removed, as the ClassLoader resource access methods will not accept it.
   * 
   * @param path
   *          the absolute path within the classpath
   * @param classLoader
   *          the class loader to load the resource with, or <code>null</code>
   *          for the thread context class loader
   */
  public ClassPathResource(String path, ClassLoader classLoader) {
    String pathToUse = PathUtils.cleanPath(path);
    if (pathToUse.startsWith("/")) {
      pathToUse = pathToUse.substring(1);
    }
    this.path = pathToUse;
    this.classLoader = (classLoader != null ? classLoader : ClassLoaderUtils
        .getDefaultClassLoader());
  }

  /**
   * Create a new ClassPathResource for Class usage. The path can be relative to
   * the given class, or absolute within the classpath via a leading slash.
   * 
   * @param path
   *          relative or absolute path within the class path
   * @param clazz
   *          the class to load resources with
   */
  @SuppressWarnings("unchecked")
  public ClassPathResource(String path, Class clazz) {
    this.path = PathUtils.cleanPath(path);
    this.clazz = clazz;
  }

  /**
   * Create a new ClassPathResource with optional ClassLoader and Class. Only
   * for internal usage.
   * 
   * @param path
   *          relative or absolute path within the classpath
   * @param classLoader
   *          the class loader to load the resource with, if any
   * @param clazz
   *          the class to load resources with, if any
   */
  @SuppressWarnings("unchecked")
  protected ClassPathResource(String path, ClassLoader classLoader, Class clazz) {
    this.path = PathUtils.cleanPath(path);
    this.classLoader = classLoader;
    this.clazz = clazz;
  }

  /**
   * Return the path for this resource (as resource path within the class path).
   */
  public final String getPath() {
    return this.path;
  }

  /**
   * Return the ClassLoader that this resource will be obtained from.
   */
  public final ClassLoader getClassLoader() {
    return (this.classLoader != null ? this.classLoader : this.clazz
        .getClassLoader());
  }

  /**
   * This implementation opens an InputStream for the given class path resource.
   */
  public InputStream getInputStream() throws IOException {
    InputStream is = null;
    if (this.clazz != null) {
      is = this.clazz.getResourceAsStream(this.path);
    } else {
      is = this.classLoader.getResourceAsStream(this.path);
    }
    if (is == null) {
      throw new FileNotFoundException(getDescription()
          + " cannot be opened because it does not exist");
    }
    return is;
  }

  /**
   * This implementation returns a URL for the underlying class path resource.
   */
  public URL getURL() throws IOException {
    URL url = null;
    if (this.clazz != null) {
      url = this.clazz.getResource(this.path);
    } else {
      url = this.classLoader.getResource(this.path);
    }
    if (url == null) {
      throw new FileNotFoundException(getDescription()
          + " cannot be resolved to URL because it does not exist");
    }
    return url;
  }

  /**
   * This implementation returns a File reference for the underlying class path
   * resource, provided that it refers to a file in the file system.
   */
  public File getFile() throws IOException {
    return ResourceUtils.getFile(getURL(), getDescription());
  }

  /**
   * This implementation determines the underlying File (or jar file, in case of
   * a resource in a jar/zip).
   */
  protected File getFileForLastModifiedCheck() throws IOException {
    URL url = getURL();
    if (ResourceUtils.isJarURL(url)) {
      URL actualUrl = ResourceUtils.extractJarFileURL(url);
      return ResourceUtils.getFile(actualUrl);
    } else {
      return ResourceUtils.getFile(url, getDescription());
    }
  }

  /**
   * This implementation creates a ClassPathResource, applying the given path
   * relative to the path of the underlying resource of this descriptor.
   */
  public Resource createRelative(String relativePath) {
    String pathToUse = PathUtils.applyRelativePath(this.path, relativePath);
    return new ClassPathResource(pathToUse, this.classLoader, this.clazz);
  }

  /**
   * This implementation returns the name of the file that this class path
   * resource refers to.
   */
  public String getFilename() {
    return PathUtils.getFileName(this.path);
  }

  /**
   * This implementation returns a description that includes the class path
   * location.
   */
  public String getDescription() {
    return "class path resource [" + this.path + "]";
  }

  /**
   * This implementation compares the underlying class path locations.
   */
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof ClassPathResource) {
      ClassPathResource otherRes = (ClassPathResource) obj;
      return (this.path.equals(otherRes.path)
          && ObjectUtils.equals(this.classLoader, otherRes.classLoader) && ObjectUtils
            .equals(this.clazz, otherRes.clazz));
    }
    return false;
  }

  /**
   * This implementation returns the hash code of the underlying class path
   * location.
   */
  public int hashCode() {
    return this.path.hashCode();
  }

  public OutputStream getOutputStream() throws IOException {

    URL resourceUrl = null;
    if (this.clazz != null) {
      resourceUrl = this.clazz.getResource(this.path);
    } else {
      resourceUrl = this.classLoader.getResource(this.path);
    }
    if (resourceUrl == null) {
      throw new FileNotFoundException(getDescription()
          + " cannot be opened because it does not exist");
    }

    URI uri = null;
    try {
      uri = resourceUrl.toURI();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    OutputStream output = null;
    if ("file".equals(uri.getScheme())) {
      File file = new File(uri);
      output = new FileOutputStream(file);
    } else {
      throw new UnsupportedOperationException("Not support " + uri.getScheme()
          + " scheme.");
    }

    return output;

  }

}
