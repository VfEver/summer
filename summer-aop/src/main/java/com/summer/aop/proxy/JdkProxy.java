package com.summer.aop.proxy;

import com.summer.aop.advice.DefaultAdvice;
import com.summer.aop.aspect.Aspect;
import com.summer.aop.pointcut.DefaultPointcut;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk dynamic proxy that agents the suitable bean.
 * @author zys
 * @date 2018/03/10
 */
public class JdkProxy implements Proxy, InvocationHandler{

    private Object target;
    private Aspect aspect;

    public JdkProxy(Object target, Aspect aspect) {

        this.target = target;
        this.aspect = aspect;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        DefaultPointcut pointcut = (DefaultPointcut) aspect.getPointcut();

        if (pointcut.matchMethod(methodName)) {

            Object advicor = (DefaultAdvice) aspect.getAdvice().getAdvicor();

        }

        Object obj = method.invoke(proxy, args);

        return obj;
    }


    @Override
    public Object getProxyObj() {

        return java.lang.reflect.Proxy.newProxyInstance(getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
