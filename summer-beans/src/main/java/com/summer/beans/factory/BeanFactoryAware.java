package com.summer.beans.factory;

/**
 * interface that get the bean factory.
 * @author zys
 * @date 2018/03/10
 */
public interface BeanFactoryAware {

    /**
     * set the bean factory.
     * @param beanFactory
     */
    void setBeanFactory(BeanFactory beanFactory);
}
