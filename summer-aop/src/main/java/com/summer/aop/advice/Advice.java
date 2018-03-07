package com.summer.aop.advice;

import com.summer.aop.pointcut.DefaultPoincut;

/**
 * advice
 * @author zys
 * @date 2018/03/07
 */
public interface Advice {

    DefaultPoincut getPointcut();
}
