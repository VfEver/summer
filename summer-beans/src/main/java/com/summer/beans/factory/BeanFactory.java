package com.summer.beans.factory;

import com.summer.beans.exception.BeanNotFindException;

/**
 * bean factory,to get bean
 * @author zys
 * @date 2018/01/08
 */
public interface BeanFactory {

    /**
     * get the bean with bean's name,
     * if it is an alias name,it will translated into canonical name.
     * @param name
     * @return
     * @throws BeanNotFindException
     */
    Object getBean (String name) throws BeanNotFindException;

    /**
     * get the bean
     * @param id
     * @param tClass
     * @param <T>
     * @return
     * @throws BeanNotFindException
     */
    <T> T getBean(String id, Class<T> tClass) throws BeanNotFindException;

    /**
     * whether the factory contains this bean with this name,
     * if the name is an alias,it will translated into canonical name.
     * @param name
     * @return
     */
    boolean containsBean(String name);

    /**
     * whether the given name of the bean is singleton.
     * @param name
     * @return
     * @throws BeanNotFindException
     */
    boolean isSingleton(String name) throws BeanNotFindException;

    /**
     * whether the given name of the bean is prototype.
     * @return
     * @throws BeanNotFindException
     */
    boolean isPrototype() throws BeanNotFindException;

    /**
     * get the named bean's class
     * @param name
     * @return
     * @throws BeanNotFindException
     */
    Class<?> getType(String name) throws BeanNotFindException;
}
