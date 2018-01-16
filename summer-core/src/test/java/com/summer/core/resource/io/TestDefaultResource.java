package com.summer.core.resource.io;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

public class TestDefaultResource extends TestCase{

    private Logger logger = LoggerFactory.getLogger(TestDefaultResource.class);

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void test01 () throws Exception {

        File file = new File("src/test/resources/file.txt");

        logger.info("file.txt is exist? - " + file.exists());
        logger.info("file.txt's absolute path is : " + file.getAbsolutePath());
        logger.info("file.txt's path is : " + file.getPath());
        logger.info("file.txt's cano is : " + file.getCanonicalPath());
        DefaultResource resource = new DefaultResource(file) {
        };
        logger.info("-------------------");
        logger.info("DefaultResource is exist? - " + resource.isExist());

        InputStream inputStream = resource.getInputStream();
        byte[] b = new byte[(int) file.length()];
        inputStream.read(b);
        logger.info(new String(b));
    }
}
