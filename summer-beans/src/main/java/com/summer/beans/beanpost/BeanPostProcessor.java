package com.summer.beans.beanpost;

import com.summer.beans.exception.BeanException;

/**
 * used to decorate bean when the bean is created.
 * @author zys
 * @date 2018/03/05
 */
public interface BeanPostProcessor {

    /**
     * before creating bean
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException;

    /**
     * after creating bean
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException;
}
