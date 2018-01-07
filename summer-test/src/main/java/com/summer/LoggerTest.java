package com.summer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * test slf4j
 * @author zys
 * @date 2018/01/07
 */
public class LoggerTest {

    private Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    public static void main(String[] args) {

        new LoggerTest().testLogger();
    }

    public void testLogger () {
        logger.info("this class is test logger({}), class is {}", "slf4j", LoggerTest.class.toString());
    }
}
