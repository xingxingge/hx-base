package com.hx.base.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hx on 16-11-8.
 */
public class PropertyPlaceholderTest {
  @Test
  public void test(){
    String xml="xxxfff${abc}fff${cdd}fff${}${ff}${${${fffssf}}}f ${xx${f44f}ff}f${f56666f}";
    List<String> list= PropertyPlaceholder.getPlaceholders(xml);
    System.out.println(list);

  }
  @Test
  public void resolvePlaceholdersTest(){
    String xml="xxxfff${abc}fff${cdd}fff${}${${$}ff";
    Map<String,String> maps=new HashMap<>();
    maps.put("abc","123");
    maps.put("cdd","456");
    maps.put("","88");
   String result= PropertyPlaceholder.resolvePlaceholders(xml,maps);
    System.out.println(result);

  }

}
