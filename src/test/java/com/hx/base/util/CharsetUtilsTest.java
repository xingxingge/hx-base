package com.hx.base.util;

import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Created by hx on 16-9-19.
 */
public class CharsetUtilsTest {

  @Test
  public void isValidUtf8() throws Exception {
    System.out.println("test");

  }

  @Test
  public void changeFileCharsetTest() throws Exception {
    String srcFile = "/home/hx/Java/file";
    String destFile = "/home/hx/Java/file.out";
    CharsetUtils.changeFileCharset(srcFile, destFile, CharsetUtils.GBK, CharsetUtils.UTF_8);
  }


  @Test
  public void isValidUtf8Test() throws IOException {
    String srcFile = "/home/hx/Java/file";
    System.out.println(CharsetUtils.isValidUtf8(srcFile));

  }

  @Test
  public void changeCharsetTest() throws UnsupportedEncodingException {
    String str = "This is a 中文的 String!";

    System.out.println("str: " + str);
    String gbk = CharsetUtils.changeCharset(str, CharsetUtils.GBK);
    System.out.println("转换成GBK码: " + gbk);

    System.out.println();
    String ascii = CharsetUtils.changeCharset(str, CharsetUtils.US_ASCII);
    System.out.println("转换成US-ASCII码: " + ascii);

    gbk = CharsetUtils.changeCharset(ascii, CharsetUtils.US_ASCII, CharsetUtils.GBK);
    System.out.println("再把ASCII码的字符串转换成GBK码: " + gbk);
    System.out.println();

    String iso88591 = CharsetUtils.changeCharset(str, CharsetUtils.ISO_8859_1);
    System.out.println("转换成ISO-8859-1码: " + iso88591);

    gbk = CharsetUtils.changeCharset(iso88591, CharsetUtils.ISO_8859_1, CharsetUtils.GBK);
    System.out.println("再把ISO-8859-1码的字符串转换成GBK码: " + gbk);
    System.out.println();

    String utf8 = CharsetUtils.changeCharset(str, CharsetUtils.UTF_8);
    System.out.println("转换成UTF-8码: " + utf8);
    gbk = CharsetUtils.changeCharset(utf8, CharsetUtils.UTF_8, CharsetUtils.GBK);
    System.out.println("再把UTF-8码的字符串转换成GBK码: " + gbk);
    System.out.println();

    String utf16be = CharsetUtils.changeCharset(str, CharsetUtils.UTF_16BE);
    System.out.println("转换成UTF-16BE码:" + utf16be);
    gbk = CharsetUtils.changeCharset(utf16be, CharsetUtils.UTF_16BE, CharsetUtils.GBK);
    System.out.println("再把UTF-16BE码的字符串转换成GBK码: " + gbk);
    System.out.println();

    String utf16le = CharsetUtils.changeCharset(str, CharsetUtils.UTF_16LE);
    System.out.println("转换成UTF-16LE码:" + utf16le);
    gbk = CharsetUtils.changeCharset(utf16le, CharsetUtils.UTF_16LE, CharsetUtils.GBK);
    System.out.println("再把UTF-16LE码的字符串转换成GBK码: " + gbk);
    System.out.println();

    String utf16 = CharsetUtils.changeCharset(str, CharsetUtils.UTF_16);
    System.out.println("转换成UTF-16码:" + utf16);
    gbk = CharsetUtils.changeCharset(utf16, CharsetUtils.UTF_16LE, CharsetUtils.GBK);
    System.out.println("再把UTF-16码的字符串转换成GBK码: " + gbk);
  }

}
