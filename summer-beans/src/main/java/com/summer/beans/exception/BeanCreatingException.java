package com.summer.beans.exception;

/**
 * bean can not be created while be creating.
 * @author zys
 * @date 201801/15
 */
public class BeanCreatingException extends RuntimeException {

    public BeanCreatingException () {

        super();
    }

    public BeanCreatingException(String errorMsg) {

        super(errorMsg);
    }
}
