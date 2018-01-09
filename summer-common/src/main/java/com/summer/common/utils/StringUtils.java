package com.summer.common.utils;

import java.util.Collection;

/**
 * String util,a tool class to deal with String conveniently.
 * @author zys
 * @date 2018/01/08
 */
public class StringUtils {

    /**
     * whether the string is null or ''
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {

        if (string == null || string.length() < 1) {
            return true;
        }
        return false;
    }

    /**
     * whether the string is not null
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {

        return !isEmpty(string);
    }

    /**
     * make the collection to array.
     * @param collection
     * @return
     */
    public static String[] toStringArray (Collection<String> collection) {

        return collection.toArray(new String[collection.size()]);
    }
}
