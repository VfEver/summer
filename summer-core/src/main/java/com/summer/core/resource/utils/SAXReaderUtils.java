package com.summer.core.resource.utils;

import org.dom4j.io.SAXReader;

/**
 * SAXReader util
 * @author zys
 * @date 2018/01/07
 */
public class SAXReaderUtils {

    private static SAXReader saxReader = new SAXReader();

    /**
     * get the saxReader
     * @return
     */
    public static SAXReader getSaxReader() {

        return saxReader;
    }

}
