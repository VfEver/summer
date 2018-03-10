package com.summer.aop.advice;

/**
 * default advicor
 * @author zys
 * @date 2018/03/10
 */
public class DefaultAdvice implements Advice {

    private Object advicor;

    public DefaultAdvice(Object advicor) {

        this.advicor = advicor;
    }

    public void setAdvicor(Object advicor) {
        this.advicor = advicor;
    }

    @Override
    public Object getAdvicor() {
        return null;
    }
}
