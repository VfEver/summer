package com.summer.aop.advice;

import java.util.HashMap;
import java.util.Map;

/**
 * default advicor
 * @author zys
 * @date 2018/03/10
 */
public class DefaultAdvice implements Advice {

    private Object advicor;
    private String advicorName;
    Map<String, String> beforeMethodInterceptor = new HashMap<>(4);
    Map<String, String> afterMethodInterceptor = new HashMap<>(4);


    public DefaultAdvice () {
    }


    public void setAdvicor(Object advicor) {
        this.advicor = advicor;
    }

    @Override
    public Object getAdvicor() {
        return advicor;
    }

    public String getAdvicorName() {
        return advicorName;
    }

    public void setAdvicorName(String advicorName) {
        this.advicorName = advicorName;
    }

    public void addBeforeMethodInterceptor (String methodName, String pointcutRef) {

        this.beforeMethodInterceptor.put(methodName, pointcutRef);
    }

    public void addAfterMethodInterceptor (String methodName, String pointcutRef) {

        this.afterMethodInterceptor.put(methodName, pointcutRef);
    }

    public Map<String, String> getBeforeMethodInterceptor() {
        return beforeMethodInterceptor;
    }

    public Map<String, String> getAfterMethodInterceptor() {
        return afterMethodInterceptor;
    }
}
