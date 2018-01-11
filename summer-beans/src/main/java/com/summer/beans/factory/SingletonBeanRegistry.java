package com.summer.beans.factory;

/**
 * Interface that defines a registry for shared bean instances.
 * @author zys
 * @date 2018/01/11
 */
public interface SingletonBeanRegistry {

    /**
     * register singleton bean in registry
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * get the singleton bean from registry
     * @param name
     * @return
     */
    Object getSingleton(String name);

    /**
     * check if this registry contains a singleton instance with the given name.
     * @param name
     * @return
     */
    boolean containsSingleton(String name);

    /**
     * get the number of singleton beans registered in this registry.
     * @return
     */
    int getSingletonCount();
}
