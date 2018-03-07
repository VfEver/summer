package com.summer.aop.pointcut;

/**
 * class filter,to determine the specify method
 * whether to need aop.
 * @author zys
 * @date 2018/03/07
 */
public interface MethodMatcher {

    boolean matchMethod(String methodName);
}
