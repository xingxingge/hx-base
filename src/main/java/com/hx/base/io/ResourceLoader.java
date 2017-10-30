package com.hx.base.io;

public interface ResourceLoader {

  Resource getResource(String location);

  ClassLoader getClassLoader();

}
