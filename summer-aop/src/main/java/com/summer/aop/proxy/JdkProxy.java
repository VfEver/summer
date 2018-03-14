package com.summer.aop.proxy;

import com.summer.aop.advice.DefaultAdvice;
import com.summer.aop.aspect.Aspect;
import com.summer.aop.pointcut.DefaultPointcut;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

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

        Object res = null;

        String methodName = method.getName();
        DefaultPointcut pointcut = (DefaultPointcut) aspect.getPointcut();
        if (pointcut.matchMethod(methodName)) {

            Object advicor = (DefaultAdvice) aspect.getAdvice().getAdvicor();
            DefaultAdvice advice = (DefaultAdvice) aspect.getAdvice();
            for (Map.Entry<String, String> entry : advice.getBeforeMethodInterceptor().entrySet()) {

                Method beforeMethod = advicor.getClass().getMethod(entry.getKey());
                beforeMethod.invoke(advicor, null);
            }
            res = method.invoke(proxy, args);
            for (Map.Entry<String, String> entry : advice.getAfterMethodInterceptor().entrySet()) {

                Method beforeMethod = advicor.getClass().getMethod(entry.getKey());
                beforeMethod.invoke(advicor, null);
            }
        }


        return res;
    }


    @Override
    public Object getProxyObj() {

        return java.lang.reflect.Proxy.newProxyInstance(getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
