package com.summer.aop.pointcut;

/**
 * pointcut
 * @author zys
 * @date 2018/03/07
 */
public class DefaultPoincut implements ClassMatcher, MethodMatcher {

    private String pointcutName;
    private String expression;

    @Override
    public boolean matchClass(String className) {
        return false;
    }

    @Override
    public boolean matchMethod(String methodName) {
        return false;
    }
}
