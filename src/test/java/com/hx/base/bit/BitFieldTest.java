package com.hx.base.bit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hx on 16-10-5.
 */
public class BitFieldTest {
  BitField b1 = new BitField();
  BitField b2 = new BitField(64,8);
  long n1 = 0b1111_1001_1101_0000_0000l;
  long n2=  0b1111_1001_1101_0000_0000l;
  @Test
  public void calcMask() throws Exception {
    System.out.println(Long.toBinaryString(b1.calcMask(6)));

  }

  @Test
  public void getSectionValue() throws Exception {
    System.out.println(Long.toBinaryString(b1.getSectionValue(n1, 8)));
    System.out.println(Long.toBinaryString(b2.getSectionValue(n2, 1)));

  }

  @Test
  public void setSectionValue() throws Exception {
    System.out.println(Long.toBinaryString(b1.setSectionValue(n1, 8,0)));
    System.out.println(Long.toBinaryString(b2.setSectionValue(n2, 1,0b1111)));

  }

}
