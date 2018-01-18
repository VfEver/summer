package com.summer.beans.factory;

import com.summer.beans.exception.BeanNotFindException;
import com.sun.istack.internal.Nullable;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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

    /**
     * the set of the bean is being created.
     * bean name,just for prototype
     */
    private Set<String> currentInCreation = Collections.newSetFromMap(new ConcurrentHashMap<>(32));

    public AbstractBeanFactory () {

        this(null);
    }

    public AbstractBeanFactory (BeanFactory parentBeanFactory) {

        this.parentBeanFactory = parentBeanFactory;
    }

    public void setParentBeanFactory(BeanFactory parentBeanFactory) {
        
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

    protected <T> T doGetBean(String name, @Nullable Class<T> clazz, @Nullable Object[] args) {

        //put it into sub-class to implement.
        return null;
    }

    /**
     * whether the given bean is being created.
     * @param name
     * @return
     */
    protected boolean prototypeInCreation(String name) {

        synchronized (this.currentInCreation) {

            return this.currentInCreation.contains(name);
        }
    }
}
