package com.summer.common.utils;

import com.summer.common.support.Assert;

/**
 * class utils,for getting Class, ClassLoader and so on.
 * @author zys
 * @date 2018/01/17
 */
public class ClassUtils {

    /**
     * get the Class by the given class name.
     * @param className
     * @return
     */
    public static Class getClassByClassName (String className) {

        Assert.hasText(className, "class name must not be empty.");
        Class clazz = null;

        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * get the ClassLoader with the given Class.
     * @param clazz
     * @return
     */
    public static ClassLoader getClassLoader (Class clazz) {

        Assert.notNull(clazz, "the class must not be null.");
        return clazz.getClassLoader();
    }
}
