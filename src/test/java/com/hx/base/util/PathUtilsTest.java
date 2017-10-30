package com.hx.base.util;

import org.junit.Test;


/**
 * Created by hx on 16-9-18.
 */
public class PathUtilsTest {
  @Test
  public void getFileName() throws Exception {
    System.out.println(PathUtils.getFileName("/F/java/study/jjj.xml"));
    System.out.println(PathUtils.getFileName("/F/java/study/jjj/"));
    System.out.println(PathUtils.getFileName("/F/java/study/jjj\\"));
    System.out.println(PathUtils.getFileName("c:\\F\\java\\study\\jj"));
    System.out.println(PathUtils.getFileName("c:\\F\\java\\study\\jj/jj"));

  }

  @Test
  public void getFilePath() throws Exception {
    System.out.println(PathUtils.getFilePath("/F/java/study/jjj.xml"));
    System.out.println(PathUtils.getFilePath("/F/java/study/jjj/"));
    System.out.println(PathUtils.getFilePath("/F/java/study/jjj\\"));
    System.out.println(PathUtils.getFilePath("c:\\F\\java\\study\\jj"));
    System.out.println(PathUtils.getFilePath("c:\\F\\java\\study\\jj/jj"));

  }

  @Test
  public void getFileNameWithoutExtension() throws Exception {
    System.out.println(PathUtils.getFileNameWithoutExtension("/F/java/study/jjj.xml"));
    System.out.println(PathUtils.getFileNameWithoutExtension("/F/java/study/jjj/"));
    System.out.println(PathUtils.getFileNameWithoutExtension("/F/java/study/jjj\\"));
    System.out.println(PathUtils.getFileNameWithoutExtension("c:\\F\\java\\study\\jj"));
    System.out.println(PathUtils.getFileNameWithoutExtension("c:\\F\\java\\study\\jj/jj"));

  }

  @Test
  public void getFileNameExtension() throws Exception {
    System.out.println(PathUtils.getFileNameExtension("/F/java/study/jjj.xml"));
    System.out.println(PathUtils.getFileNameExtension("/F/java/study/jjj"));

  }

  @Test
  public void stripFileNameExtension() throws Exception {
    System.out.println(PathUtils.stripFileNameExtension("/F/java/study/jjjxml"));


  }

  @Test
  public void applyRelativePath() throws Exception {
    System.out.println(PathUtils.applyRelativePath("c:\\F\\java///","tt"));


  }

  @Test
  public void cleanPath() throws Exception {
    System.out.println(PathUtils.cleanPath("c:\\F\\java"));
    System.out.println(PathUtils.cleanPath("../../ff/ff/ffc"));
    System.out.println(PathUtils.cleanPath("file:/core/../../core/io/Resource.class"));


  }

  @Test
  public void translatePath2CanonicalPath() throws Exception {
    String path="/ff/ff/ffc\\";
    path=PathUtils.cleanPath(path);
    System.out.println(PathUtils.translatePath2CanonicalPath(path));
    System.out.println(PathUtils.translatePath2CanonicalPath("/f/f"));

  }

}
