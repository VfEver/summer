package com.summer.beans.factory;

import com.summer.beans.exception.BeanNotFindException;
import com.summer.common.support.Assert;

/**
 * abstract bean factory,implementing BeanFactory,
 * so this class implements common useful method.
 * @author zys
 * @date 2018/01/09
 */
public abstract class AbstractBeanFactory extends SimpleAliasRegistry implements BeanFactory {

    /**
     * parent beanfactory, for bean inheritance support
     */
    private BeanFactory parentBeanFactory;

    public AbstractBeanFactory () {

        this(null);
    }

    public AbstractBeanFactory (BeanFactory parentBeanFactory) {

        this.parentBeanFactory = parentBeanFactory;
    }

    @Override
    public Object getBean(String name) throws BeanNotFindException {
        return null;
    }

    @Override
    public <T> T getBean(String id, Class<T> tClass) throws BeanNotFindException {
        return null;
    }

    @Override
    public Class<?> getType(String name) throws BeanNotFindException {
        return null;
    }

    @Override
    public boolean isSingleton(String name) throws BeanNotFindException {
        return false;
    }

    @Override
    public boolean isPrototype() throws BeanNotFindException {
        return false;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }
}
