package com.summer.core.resource.io;

import java.io.*;

/**
 * abstract class for resource
 * @author zys
 * @date 2018/01/06
 */
public abstract class AbstractResource implements InputStreamResource{

    private File file;
    private String filePath;

    public AbstractResource () {
    }

    public AbstractResource (File file) {

        this(file, file.getAbsolutePath());
    }

    public AbstractResource (String filePath) {

        this(new File(filePath), filePath);
    }

    public AbstractResource (File file, String filePath) {

        this.file = file;
        this.filePath = filePath;
    }

    @Override
    public InputStream getInputStream() throws IOException {

        if (!isExist()) {

            throw new FileNotFoundException("file is not exist");
        }

        InputStream inputStream = new FileInputStream(file);
        return inputStream;
    }

    @Override
    public boolean isExist() {

        if (null == file || !file.exists()) {

            return false;
        }
        return true;
    }

}
