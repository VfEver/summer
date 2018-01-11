package com.summer.beans.exception;

import com.summer.beans.factory.SingletonBeanRegistry;

/**
 * the sigleton instance has been registered in factory,
 * then will throw this exception
 * @author zys
 * @date 2018/01/11
 */
public class SingletonBeanExistException extends IllegalStateException {

    public SingletonBeanExistException() {

        super();
    }

    public SingletonBeanExistException (String errorMsg) {

        super(errorMsg);
    }
}
