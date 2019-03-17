package com.hx.base;

import java.util.Date;

public class Main {
  public static void main(String[] args) {
    System.out.println("==============");
    while(true){
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(new Date());
    }
  }
}
