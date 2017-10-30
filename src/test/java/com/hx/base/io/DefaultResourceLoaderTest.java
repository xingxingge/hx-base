package com.hx.base.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hx on 16-9-14.
 */
public class DefaultResourceLoaderTest {
  @Test
  public void getResource() throws Exception {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource=resourceLoader.getResource("http://baidu.com");
    System.out.println(resource.getURI());
    System.out.println(resource.getURL());
    System.out.println(resource.getDescription());
    InputStream inputStream = resource.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String line;
    while ((line=reader.readLine())!=null) {
      System.out.println(line);
    }

  }

}
