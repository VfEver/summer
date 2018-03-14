package com.summer.aop.proxy;

import com.summer.aop.aspect.Aspect;

/**
 * a factory can create proxy class depends on
 * class attributes which would be created.
 * @author zys
 * @date 2018/01/13
 */
public class ProxyFactory {

    /**
     * create the proper proxy.
     * has implemented interfaces,use jdk dynamic proxy,
     * else use cglib proxy.
     * @param object
     * @return
     */
    public static Proxy createProxy(Object object, Aspect aspect) {

        Class[] interfaces = object.getClass().getInterfaces();
        if (interfaces == null || interfaces.length <= 0) {

            return new Cglibproxy();
        } else {

            return new JdkProxy(object, aspect);
        }
    }
}
