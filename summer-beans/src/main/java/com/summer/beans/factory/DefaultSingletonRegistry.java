package com.summer.beans.factory;

import com.summer.beans.exception.SingletonBeanExistException;
import com.summer.common.logger.CommonLogger;
import com.summer.common.support.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * default singleton registry, to implement the interface
 * SingletonBeanRegistry.
 * @author zys
 * @date 2018/01/11
 */
public class DefaultSingletonRegistry extends SimpleAliasRegistry
        implements SingletonBeanRegistry {

    private Logger logger = CommonLogger.getLogger(getClass());
    private String logInfo = "singleton instance operation - ";
    /**
     * singleton bean map,bean name -> bean instance
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>(128);
    /**
     * the map that indicates the bean is early created. bean name -> bean instance TODO need consider
     */
    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(32);
    /**
     * the set that indicates the bean is being created.
     */
    private Set<String> singletonCurrentlyInCreation = Collections.newSetFromMap(new ConcurrentHashMap<>(32));

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {

        Assert.hasText(beanName, "the singleton bean's name must not be empty.");
        Assert.notNull(singletonObject, "the singleton instance must not be null.");

        synchronized (this.singletonObjects) {

            Object mayExistObject = this.singletonObjects.get(beanName);
            if (mayExistObject != null) {

                logger.error(logInfo + "singleton registry has already has this bean, name - {}", beanName);
                String errorMsg = "singleton registry has already has this bean - " + beanName;
                throw new SingletonBeanExistException(errorMsg);
            }
            logger.debug(logInfo + "add instance named {} into factory", beanName);
            this.singletonObjects.put(beanName, singletonObject);
        }
    }

    @Override
    public Object getSingleton(String name) {

        Object singletonObject = null;
        synchronized (this.singletonObjects) {

            singletonObject = this.singletonObjects.get(name);
        }
        return singletonObject;
    }

    @Override
    public boolean containsSingleton(String name) {

        return this.singletonCurrentlyInCreation.contains(name) ||
                this.earlySingletonObjects.containsKey(name) ||
                this.singletonObjects.containsKey(name);
    }

    @Override
    public int getSingletonCount() {

        int count = 0;
        synchronized (this.singletonObjects) {

            logger.debug(logInfo + "get the number of the singleton instance.");
            count = this.singletonObjects.size();
        }
        return count;
    }

    /**
     * add the bean instance into the singleton registry.
     * @param beanName
     * @param singletonObject
     */
    protected void addSingleton(String beanName, Object singletonObject) {

        synchronized (this.singletonObjects) {
            if (!this.singletonObjects.containsKey(beanName)) {

                this.singletonObjects.put(beanName, singletonObject);
                this.singletonCurrentlyInCreation.remove(beanName);
            }
        }
    }

    protected boolean isCurrentInCreation(String name) {

        return this.singletonCurrentlyInCreation.contains(name);
    }

    /**
     * destroy the singleton instance,use for subclass.
     */
    protected void destroySingleton() {

        synchronized (this.singletonObjects) {

            logger.debug(logInfo + "destroy singleton factory's shared bean instance.");
            this.singletonObjects.clear();
            this.earlySingletonObjects.clear();
            this.singletonCurrentlyInCreation.clear();
        }
    }
}
