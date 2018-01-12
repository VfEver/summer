package com.summer.beans.factory;

import com.summer.beans.exception.BeanNotFindException;
import com.summer.common.support.Assert;
import com.sun.istack.internal.Nullable;

import java.util.Map;

/**
 * abstract bean factory,implementing BeanFactory,
 * so this class implements common useful method.
 * @author zys
 * @date 2018/01/09
 */
public abstract class AbstractBeanFactory extends DefaultSingletonRegistry implements BeanFactory {

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

        return doGetBean(name, null, null);
    }

    @Override
    public <T> T getBean(String id, Class<T> tClass) throws BeanNotFindException {

        return doGetBean(id, tClass, null);
    }

    @Override
    public Class<?> getType(String name) throws BeanNotFindException {

        return null;
    }

    @Override
    public boolean isSingleton(String name) throws BeanNotFindException {

        return containsSingleton(name);
    }

    @Override
    public boolean isPrototype() throws BeanNotFindException {

        return false;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    protected <T> T doGetBean(String name, @Nullable Class<?> clazz, @Nullable Object[] args) {

        return null;
    }
}
