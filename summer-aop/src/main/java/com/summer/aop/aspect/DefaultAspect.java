package com.summer.aop.aspect;

import com.summer.aop.advice.Advice;
import com.summer.aop.pointcut.Pointcut;

/**
 * default aspect
 * @author zys
 * @date 2018/03/10
 */
public class DefaultAspect implements Aspect {

    private Pointcut pointcut;
    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
