package com.hx.base.exception;


/**
 * Created by hx on 16-9-13.
 */
public class BaseException extends Exception {
  private static final long serialVersionUID = 1L;
  private String exceptionCode;

  public BaseException(String message, Throwable cause) {
    super(message, cause);
    if (cause instanceof BaseException) {
      exceptionCode = ((BaseException) cause).exceptionCode;
    }
  }

  public BaseException(String message) {
    super(message);
  }

  public BaseException(Throwable cause) {
    super(cause);
    if (cause instanceof BaseException) {
      exceptionCode = ((BaseException) cause).exceptionCode;
    }
  }

  public String getExceptionCode() {
    return exceptionCode;
  }

  public void setExceptionCode(String exceptionCode) {
    this.exceptionCode = exceptionCode;
  }
}
