package com.summer.beans.factory;

import com.summer.beans.exception.BeanNotFindException;
import com.summer.beans.exception.CircleReferenceException;
import com.summer.common.logger.CommonLogger;
import com.summer.common.support.Assert;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * abstract bean factory,implementing BeanFactory,
 * so this class implements common useful method.
 * @author zys
 * @date 2018/01/09
 */
public abstract class AbstractBeanFactory extends DefaultSingletonRegistry implements BeanFactory {

    private Logger logger = CommonLogger.getLogger(AbstractBeanFactory.class);
    private String logInfo = "abstract bean factory, for sub class - ";

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

    protected <T> T doGetBean(String name, @Nullable Class<T> clazz, @Nullable Object[] args) {

        //make the name is canonical name,not alias name
        String canonicalName = getCanonicalName(name);

        T object = null;
        //first get the bean from sington registry
        if (containsSingleton(canonicalName)) {

            if (logger.isDebugEnabled()) {
                logger.debug(logInfo + "the singleton registry has this bean named - {}", canonicalName);
            }

            if (isCurrentInCreation(canonicalName)) {
                logger.debug(logInfo + "this singleton bean is creating.");
                //TODO expose the factory object,resolve circle dependency.
                return null;
            }

            object = (T) getSingleton(canonicalName);
            //is singleton and haven't been created.
            if (object == null) {

                //start to create the singleton bean and put it in cache.
            }
            return (T) getSingleton(canonicalName);
        } else {

            //not a singleton object,check the circle dependency.
            if (prototypeInCreation(canonicalName)) {

                logger.error(logInfo + "the bean is prototype bean,occurred circle dependency.bean name - {}", canonicalName);
                throw new CircleReferenceException("the bean is prototype bean named '" +
                        canonicalName + "',occurred circle dependency");
            }

            //begin to create prototype bean
        }

        return object;
    }

    /**
     * whether the given bean is being created.
     * @param name
     * @return
     */
    private boolean prototypeInCreation(String name) {

        synchronized (this.currentInCreation) {

            return this.currentInCreation.contains(name);
        }
    }
}
