package com.summer.aop.aspect;

import com.summer.aop.advice.Advice;
import com.summer.aop.pointcut.Pointcut;

public interface Aspect {

    Pointcut getPointcut();
    Advice getAdvice();
}
