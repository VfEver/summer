package com.summer.beans.exception;

/**
 * in xml,the xml symbol can not parse.
 * @author zys
 * @date 2018/01/23
 */
public class SymbolNotDefinedException extends RuntimeException{

    public SymbolNotDefinedException () {

        super();
    }

    public SymbolNotDefinedException (String errorMsg) {

        super(errorMsg);
    }
}
