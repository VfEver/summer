package com.summer.common.support;

import com.summer.common.utils.StringUtils;

/**
 * assert like in java.
 * @author zys
 * @date 2018/01/08
 */
public class Assert {

    /**
     * whether the text is null
     * @param text
     * @param message
     */
    public static void hasText(String text, String message) {

        if (StringUtils.isEmpty(text)) {
            throw new IllegalArgumentException(message);
        }
    }
}
