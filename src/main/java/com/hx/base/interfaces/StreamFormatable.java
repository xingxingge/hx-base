package com.hx.base.interfaces;

import com.hx.base.exception.BaseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamFormatable {
    
    void importObject(InputStream is) throws BaseException, IOException;
    
    void exportObject(OutputStream os) throws BaseException, IOException;
    
}
