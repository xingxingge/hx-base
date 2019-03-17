package com.hx.base;

public class Main {
  public static void main(String[] args) {
    System.out.println("==============");
    Object obj = new Object();
    try {
      obj.wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
