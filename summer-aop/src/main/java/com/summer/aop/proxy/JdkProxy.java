package com.summer.aop.proxy;

import com.summer.aop.advice.Advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk dynamic proxy that agents the suitable bean.
 * @author zys
 * @date 2018/03/10
 */
public class JdkProxy implements Proxy, InvocationHandler{

    private Object target;
    private Advice advice;

    public JdkProxy(Object target) {

        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //todo whether the judgment is in conformity with the invoke method.
        return null;
    }
}
