package com.hx.base.xml;


import com.hx.base.exception.BaseException;


public class XmlAccessException extends BaseException {

    private static final long serialVersionUID = 1L;
    public XmlAccessException(String message) {
        super(message);
    }
    public XmlAccessException(Throwable e) {
        super(e);
    }
    public XmlAccessException(String message, Throwable e) {
        super(message, e);
    }
}
