package com.summer.beans.exception;

/**
 * this runtime exception occur in getting prototype bean which is being created.
 * @author zys
 * @date 2018/01/13
 */
public class CircleReferenceException extends RuntimeException {

    public CircleReferenceException () {

        super();
    }

    public CircleReferenceException (String errorMsg) {

        super(errorMsg);
    }
}
