package com.summer.core.resource.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * resource interface,simple interface for getting the InputStream.
 * @author zys
 * @date 2018/01/06
 */
public interface Resource {

    /**
     * get the InputStream
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
