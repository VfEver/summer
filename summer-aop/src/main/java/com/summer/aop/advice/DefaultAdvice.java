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
    Map<String, String> methodInterceptor = new HashMap<>(4);

    public DefaultAdvice () {
    }


    public void setAdvicor(Object advicor) {
        this.advicor = advicor;
    }

    @Override
    public Object getAdvicor() {
        return null;
    }

    public String getAdvicorName() {
        return advicorName;
    }

    public void setAdvicorName(String advicorName) {
        this.advicorName = advicorName;
    }
}
