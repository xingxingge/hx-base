package com.hx.base.util;

import java.io.*;

/**
 * 转换字符串的编码
 */
public class CharsetUtils {
  /**
   * 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
   */
  public static final String US_ASCII = "US-ASCII";

  /**
   * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
   */
  public static final String ISO_8859_1 = "ISO-8859-1";

  /**
   * 8 位 UCS 转换格式
   */
  public static final String UTF_8 = "UTF-8";

  /**
   * 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序
   */
  public static final String UTF_16BE = "UTF-16BE";

  /**
   * 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序
   */
  public static final String UTF_16LE = "UTF-16LE";

  /**
   * 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
   */
  public static final String UTF_16 = "UTF-16";

  /**
   * 中文超大字符集
   */
  public static final String GBK = "GBK";


  /**
   * 用新的字符集生成字符串
   *
   * @param str
   * @param newCharset
   * @return
   * @throws UnsupportedEncodingException
   */
  public static String changeCharset(String str, String newCharset)
          throws UnsupportedEncodingException {
    if (str == null) return null;
    //用默认字符编码解码字符串。
    byte[] bs = str.getBytes();
    //用新的字符编码生成字符串
    return new String(bs, newCharset);
  }

  /**
   * 字符串编码转换的实现方法
   *
   * @param str        待转换编码的字符串
   * @param oldCharset 原编码
   * @param newCharset 目标编码
   * @return
   * @throws UnsupportedEncodingException
   */

  public static String changeCharset(String str, String oldCharset, String newCharset)
          throws UnsupportedEncodingException {
    if (str == null) return null;
    //用旧的字符编码解码字符串。解码可能会出现异常。
    byte[] bs = str.getBytes(oldCharset);
    //用新的字符编码生成字符串
    return new String(bs, newCharset);
  }

  /**
   * 按照指定字符集读取文件
   *
   * @param fileName
   * @param encoding
   * @return
   */
  public  static String readFileByCharset(String fileName, String encoding) throws IOException {
    StringBuffer sb = new StringBuffer();
    BufferedReader in = new BufferedReader(new InputStreamReader(//
            new FileInputStream(fileName), encoding));
    String line;
    while ((line = in.readLine()) != null) {
      sb.append(line);
      sb.append("\n");
    }
    in.close();
    return sb.toString();
  }

  /**
   * 按照指定字符集写出文件
   *
   * @param fileName
   * @param encoding
   * @param str
   */
  public static void writeByCharset(String fileName, String encoding, String str) throws IOException {
    Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(fileName), encoding));
    out.write(str);
    out.close();
  }

  /**
   *
   * @param srcFile
   * @param destfile
   * @param oldCharset
   * @param newCharset
   * @throws Exception
   */
  public static void changeFileCharset(String srcFile, String destfile, String oldCharset, String newCharset) throws Exception {
    String str = readFileByCharset(srcFile, oldCharset);
    writeByCharset(destfile, newCharset, str);
  }

  public static boolean isValidUtf8(String path) throws IOException {
    File file = new File(path);
    InputStream ios = new FileInputStream(file);
    byte[] b = new byte[3];
    ios.read(b);
    ios.close();
    if (b[0] == -17 && b[1] == -69 && b[2] == -65) return true;
    return false;
  }

}
