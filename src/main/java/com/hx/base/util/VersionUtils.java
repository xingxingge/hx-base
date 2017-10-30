package com.hx.base.util;

import com.hx.base.exception.BaseException;
import com.hx.base.util.string.StringFormater;
import org.apache.commons.lang.Validate;

public abstract class VersionUtils {

  private static final String VERSION_SEPARATOR = ".";
  private static final String regString="([0-9]+\\.)*[0-9]+";

  private VersionUtils(){}

  public static String next(String currentVersion) {
    Validate.notEmpty(currentVersion, "currentVersion is empty!");
    String[] segments = currentVersion.split("\\"+VERSION_SEPARATOR);
    int[] numbers = toNumbers(segments);
    numbers = next(numbers);
    return toString(numbers);
  }

  protected static int[] toNumbers(String[] segments) {
    int[] numbers = new int[segments.length];
    for (int i = 0; i < segments.length; i++) {
      numbers[i] = Integer.valueOf(segments[i]);
    }
    return numbers;
  }

  protected static int[] next(int[] numbers) {
    int carry;
    for (int i = numbers.length-1; i >= 0; i--) {
      if (i == 0) {
        numbers[i]++;
      } else {
        if (numbers[i] < 9) {
          numbers[i]++;
          carry = 0;
        } else {
          carry = 1;
          numbers[i] = 0;
        }
        if (carry == 0)
          break;
      }
    }
    return numbers;
  }


  public static int compare(String version1,String version2) {
    int[] numbers1 = toNumbers(version1.split("\\"+VERSION_SEPARATOR));
    int[] numbers2 = toNumbers(version2.split("\\"+VERSION_SEPARATOR));
    if (numbers1.length>numbers2.length){
      int[] temp=new int[numbers1.length];
      System.arraycopy(numbers2,0,temp,numbers1.length-numbers2.length,numbers2.length);
      numbers2=temp;
    }else if (numbers1.length<numbers2.length){
      int[] temp=new int[numbers2.length];
      System.arraycopy(numbers1,0,temp,numbers2.length-numbers1.length,numbers1.length);
      numbers1=temp;
    }
    for (int i = 0; i < numbers1.length ; i++) {
      if (numbers1[i]>numbers2[i])return 1;
      else if (numbers1[i]<numbers2[i])return -1;
    }
    return 0;
  }

  public static void   validate(String version) throws BaseException {
    Validate.notEmpty(version,"version is empty");
    if (!RegExpUtils.isMatched(regString,version)){
      throw new BaseException(StringFormater.format("版本字符串{}不正确,应为{}",version,"xx.xx"));
    }
  }

  protected static String toString(int[] numbers) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < numbers.length; i++) {
      if (i==0)
      sb.append(numbers[i]);
      else {
        sb.append(VERSION_SEPARATOR);
        sb.append(numbers[i]);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) throws BaseException {
    String version="99.199";
    System.out.println(next(version));
    validate("100.100.11.11");
    int[] a=new int[10];
    System.out.println(a);
    System.out.println(compare("10.1.1","3.3.3.3"));
  }

}
