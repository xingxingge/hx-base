package com.hx.base.util.string;

/**
 * Created by hx on 16-12-5.
 */
public class StringUtils {

  public StringUtils() {
    super();
  }

  public static boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }

  public static boolean isBlank(String str) {
    int strLen;
    if (str == null || (strLen = str.length()) == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
//      if (!Character.isWhitespace(str.charAt(i))) {
//        return false;
//      }
      char c = str.charAt(i);
      if (c != 9 && c != 10 && c != '\u000B' && c != '\u000C' && c != 13
              && c != '\u001C' && c != '\u001D' && c != '\u001E' && c != '\u001F'
              && c!='\u0020'
              ) return false;
    }
    return true;
  }


  public static boolean isNotBlank(String str) {
    return !StringUtils.isBlank(str);
  }


}
