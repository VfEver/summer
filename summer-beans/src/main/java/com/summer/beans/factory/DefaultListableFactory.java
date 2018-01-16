package com.summer.beans.factory;

import com.summer.beans.bean.BeanDefinition;
import com.summer.beans.exception.BeanCreatingException;
import com.summer.beans.exception.BeanNotFindException;
import com.summer.beans.exception.CircleReferenceException;
import com.summer.common.logger.CommonLogger;
import com.summer.common.support.Assert;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * default bean factory,the complete class which can holed the beans and create beans.
 * @author zys
 * @date 2018/01/15
 */
public class DefaultListableFactory extends AbstractBeanFactory {

    private Logger logger = CommonLogger.getLogger(DefaultListableFactory.class);
    private String logInfo = "default bean factory - ";

    /**
     * bean name -> attribute
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>(32);

    public DefaultListableFactory () {

        super();
    }

    public DefaultListableFactory (BeanFactory beanFactory) {

        super(beanFactory);
    }
    @Override
    public boolean containsBean(String name) {

        String canonicalName = getCanonicalName(name);
        return containsSingleton(canonicalName);
    }

    @Override
    public boolean isSingleton(String name) throws BeanNotFindException {

        String canonicalName = getCanonicalName(name);
        return containsSingleton(canonicalName);
    }

    @Override
    public boolean isPrototype(String name) throws BeanNotFindException {

        String canonicalName = getCanonicalName(name);
        return !containsSingleton(canonicalName);
    }

    @Override
    public Class<?> getType(String name) throws BeanNotFindException {

        Assert.hasText(name, "the bean's name can not be empty.");
        String canonicalName = getCanonicalName(name);
        if (beanDefinitionMap.containsKey(canonicalName)) {

            return this.beanDefinitionMap.get(canonicalName).getBeanClazz();
        } else {

            throw new BeanNotFindException("the bean '" + name + "' doesn't exist.");
        }
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
                BeanDefinition beanDefinition = this.beanDefinitionMap.get(canonicalName);
                if (beanDefinition == null) {

                    throw new BeanCreatingException("singleton bean definition not find,name - '" + name + "'");
                }

                //create bean
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

            BeanDefinition beanDefinition = this.beanDefinitionMap.get(canonicalName);
            if (beanDefinition == null) {

                throw new BeanCreatingException("prototype bean definition not find,name - '" + name + "'");
            }

            //create bean
        }

        return object;
    }

    /**
     * destory Factory
     */
    public void destoryFactory() {

        super.destroySingleton();
        if (this.beanDefinitionMap !=null) {

            this.beanDefinitionMap.clear();
        }
    }

}
