package com.hx.base.exception;

/**
 * Created by hx on 16-9-13.
 */
public class BaseRuntimeException  extends  RuntimeException{
  private String exceptionCode;
  public BaseRuntimeException(String message) {
    super(message);
  }

  public BaseRuntimeException(String message, Throwable cause) {
    super(message, cause);
    if (cause instanceof BaseRuntimeException) {
      exceptionCode = ((BaseRuntimeException) cause).exceptionCode;
    }
  }

  public BaseRuntimeException(Throwable cause) {
    super(cause);
    if (cause instanceof BaseRuntimeException) {
      exceptionCode = ((BaseRuntimeException) cause).exceptionCode;
    }
  }

  public String getExceptionCode() {
    return exceptionCode;
  }

  public void setExceptionCode(String exceptionCode) {
    this.exceptionCode = exceptionCode;
  }
}
