
package com.hx.base.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @author Taiding Tang
 *
 */
public interface StreamSource {
    
    InputStream getInputStream() throws IOException;
	
    OutputStream getOutputStream() throws IOException;

}
