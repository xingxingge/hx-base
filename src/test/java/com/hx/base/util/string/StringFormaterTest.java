package com.hx.base.util.string;


import org.junit.Test;


/**
 * Created by hx on 16-9-14.
 */
public class StringFormaterTest {

  @Test
  public void format() throws Exception {
    String str="My name \\{\\}is \\\\{},and I'm from {},I'm {},thanks.";
    String result=StringFormater.format(str,new Object[]{new String[]{"huangxing"}},"China",15);
    System.out.println(result);

  }

  @Test
  public void isEscapedDelimeter() throws Exception {

  }

  @Test
  public void isDoubleEscaped() throws Exception {

  }

}
