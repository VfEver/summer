package com.summer.aop.pointcut;

/**
 * class filter,to determine the specify class
 * whether to need aop.
 * @author zys
 * @date 2018/03/07
 */
public interface ClassMatcher {

    /**
     * whether the class matches.
     * @param className
     * @return
     */
    boolean matchClass(String className);
}
