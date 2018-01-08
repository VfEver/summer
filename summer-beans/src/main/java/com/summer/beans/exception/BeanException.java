package com.summer.beans.exception;

/**
 * when can not find the specify bean,throw is exception.
 * @author zys
 * @date 2018/01/08
 */
public class BeanException extends Exception {

    public BeanException() {

        super();
    }

    public BeanException(String errorMsg) {

        super(errorMsg);
    }
}
