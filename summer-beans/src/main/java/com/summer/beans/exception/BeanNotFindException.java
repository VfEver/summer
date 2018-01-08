package com.summer.beans.exception;

/**
 * when can not find the specify bean,throw is exception.
 * @author zys
 * @date 2016/01/08
 */
public class BeanNotFindException extends BeanException {

    public BeanNotFindException () {

        super();
    }

    public BeanNotFindException (String errorMsg) {

        super(errorMsg);
    }
}
