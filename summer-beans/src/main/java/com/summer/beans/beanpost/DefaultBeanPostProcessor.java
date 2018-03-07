package com.summer.beans.beanpost;

import com.summer.beans.exception.BeanException;

/**
 * default
 * @author zys
 * @date 2018/03/07
 */
public class DefaultBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
