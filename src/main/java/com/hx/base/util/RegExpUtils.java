package com.hx.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 */
public abstract class RegExpUtils {

  public static final String DOUBLE_REG_STRING = "[-+]?\\d+(\\.\\d+)?";
  public static final String LONG_REG_STRING = "[-+]?\\d+";
  public static final String W_REG_STRING = "[\\w]+";

  public static String getFirstMatched(Pattern pattern, String string) {
    Matcher matcher = pattern.matcher(string);
    if (matcher.find()) {
      return matcher.group();
    }
    return null;
  }

  public static String getFirstMatched(String regString, String string) {
    if (regString == null || string == null) {
      return null;
    }
    Matcher matcher = Pattern.compile(regString).matcher(string);
    if (matcher.find()) {
      return matcher.group(0);
    }
    return null;
  }


  public static boolean isMatched(String regString, String string) {
    if (regString == null || string == null) {
      return false;
    }
    Matcher matcher = Pattern.compile(regString).matcher(string);
    if (matcher.matches()) {
      return true;
    }
    return false;
  }
}
