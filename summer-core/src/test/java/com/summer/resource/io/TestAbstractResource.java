package com.summer.resource.io;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

public class TestAbstractResource extends TestCase{

    private Logger logger = LoggerFactory.getLogger(TestAbstractResource.class);

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

        File file = new File("src/resource/file.txt");

        System.out.println("file.txt is exist? - " + file.exists());
        System.out.println("file.txt's absolute path is : " + file.getAbsolutePath());
        System.out.println("file.txt's path is : " + file.getPath());
        System.out.println("file.txt's cano is : " + file.getCanonicalPath());
        AbstractResource resource = new AbstractResource(file) {
        };
        System.out.println("-------------------");
        System.out.println("AbstractResource is exist? - " + resource.isExist());

        InputStream inputStream = resource.getInputStream();
        byte[] b = new byte[(int) file.length()];
        inputStream.read(b);
        System.out.println(new String(b));
    }
}
