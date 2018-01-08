package com.summer.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logger class,get logger
 * @author zys
 * @date 108/01/07
 */
public class CommonLogger {

    /**
     * get the specific class logger
     * @param clazz
     * @return
     */
    public static Logger getLogger(Class clazz) {

        return LoggerFactory.getLogger(clazz);
    }
}
