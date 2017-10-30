package com.hx.base.util;

import org.apache.commons.lang.Validate;

/**
 * Created by hx on 16-9-20.
 */
public class EnumUtils {
  public static <T> T getMatchedEnum(Class<T> clazz, String string) {
    Validate.notNull(string);
    for (T obj : clazz.getEnumConstants()) {
      if (string.equals(obj.toString())) {
        return obj;
      }
    }
    throw new IllegalArgumentException("无匹配枚举值");
  }


}
