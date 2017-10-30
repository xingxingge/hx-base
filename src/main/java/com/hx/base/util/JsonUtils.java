package com.hx.base.util;

import java.io.*;

/**
 * Created by hx on 16-9-19.
 */
public class JsonUtils {
  /**
   * 把字符串转换成json可以识别的行
   *
   * @param data
   * @return
   */
  public static String toOneLine(String data) {
    if (data == null) return data;
    StringBuilder sb = new StringBuilder(data.length() + 50);
    int beginIndex = 0;
    for (int i = 0; i < data.length(); i++) {
      char bt = data.charAt(i);
      if (bt == '"') {
        sb.append(data.substring(beginIndex, i));
        sb.append("\\\"");
        beginIndex = i + 1;
      } else if (bt == '\t') {
        sb.append(data.substring(beginIndex, i));
        sb.append("\\t");
        beginIndex = i + 1;
      } else if (bt == '\n') {
        if (i>1 && data.charAt(i-1)=='\r')
        sb.append(data.substring(beginIndex, i-1));
        else
          sb.append(data.substring(beginIndex, i));
        sb.append("\\n");
        beginIndex = i + 1;
      } else {
        if (i == data.length() - 1) {
          sb.append(data.substring(beginIndex));
        }
      }
    }
    return sb.toString();
  }

  public static String oneLine2Pretty(String data) {
    if (data == null) return data;
    StringBuilder sb = new StringBuilder(data.length());
    int beginIndex = 0;
    for (int i = 0; i < data.length(); i++) {
      char bt = data.charAt(i);
      if (bt == '\\') {
        if (i == data.length() - 1) {
          sb.append(data.substring(beginIndex));
          break;
        }
        char next = data.charAt(i + 1);
        if (next == 'n') {
          sb.append(data.substring(beginIndex, i));
          sb.append("\n");
          beginIndex = ++i + 1;
        } else if (next == '"') {
          sb.append(data.substring(beginIndex, i));
          sb.append("\"");
          beginIndex = ++i + 1;
        } else if (next == 't') {
          sb.append(data.substring(beginIndex, i));
          sb.append("\t");
          beginIndex = ++i + 1;
        } else if (next == '/') {
          sb.append(data.substring(beginIndex, i));
          sb.append("/");
          beginIndex = ++i + 1;
        }
      } else if (i == data.length() - 1) {
        sb.append(data.substring(beginIndex));
      }
    }
    return sb.toString();
  }

  public static String toOneLine(InputStream inputstream) throws IOException {
    return toOneLine(IOUtils.toString(inputstream));
  }

  public static String oneLine2Pretty(InputStream inputStream) throws IOException {
    return oneLine2Pretty(IOUtils.toString(inputStream));
  }

}
