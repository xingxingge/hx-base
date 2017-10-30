package com.hx.base.util;


import com.hx.base.io.DefaultResourceLoader;
import com.hx.base.io.Resource;
import com.hx.base.io.ResourceLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by hx on 16-10-24.
 */
public class IOUtils {

  public static String toString(String fileName) throws IOException {
   return toString(fileName,Charset.defaultCharset().toString());
  }

  public static String toString(String fileName,String charSet) throws IOException {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource(fileName);
    InputStream fis = null;
    try {
      fis = resource.getInputStream();
     return  toString(fis,charSet);
    } catch (IOException e) {
      throw e;
    } finally {
      try {
        if (fis != null)
          fis.close();
      } catch (IOException e) {
        throw e;
      }
    }
  }

  public static String toString(InputStream is) throws IOException {
    return toString(is, Charset.defaultCharset().toString());
  }
  public static String toString(InputStream is,String charSet) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] bytes = new byte[1024];
    int length;
    while ((length = is.read(bytes)) > 0) {
      baos.write(bytes, 0, length);
    }
    return  new String(baos.toByteArray(),charSet);
  }
}
