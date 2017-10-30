package com.hx.base.util.string;

import org.junit.Test;

/**
 * Created by hx on 16-12-5.
 */
public class StringUtilsTest{


  @Test
  public void blankTest(){
    String a = "黄星";
    a="  ";
    System.out.println(StringUtils.isBlank(a));
  }


}
