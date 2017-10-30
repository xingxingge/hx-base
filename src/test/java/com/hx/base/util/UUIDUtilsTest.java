package com.hx.base.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hx on 16-9-29.
 */
public class UUIDUtilsTest {
  @Test
  public void UUID() throws Exception {
    System.out.println(UUIDUtils.UUID());

  }

  @Test
  public void compressedUUID() throws Exception {
    System.out.println(UUIDUtils.compressedUUID());

  }

  @Test
  public void compactUUID() throws Exception {
    System.out.println(UUIDUtils.compactUUID());

  }

  @Test
  public void compressTest() throws Exception {
    System.out.println(UUIDUtils.compress("bbfe907e-32c9-4cc9-aed1-7c30ef80675c"));

  }

  @Test
  public void long2bytes() throws Exception {

  }


  @Test
  public void uncompress() throws Exception {
    System.out.println(UUIDUtils.uncompress("u_6QfjLJTMmu0Xww74BnXA"));

  }

  @Test
  public void bytes2long() throws Exception {

  }

}
