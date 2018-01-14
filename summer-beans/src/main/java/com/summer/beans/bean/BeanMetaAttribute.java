package com.summer.beans.bean;

import com.summer.beans.enums.BeanScopeEnum;

/**
 * bean's meta attributes definition.
 * @author zys
 * @date 2018/01/07
 */
public interface BeanMetaAttribute {

    /**
     * get the bean's Class.
     * @param name
     * @return
     */
    Class<?> getClass(String name);

    /**
     * get the scope of the bean.
     * @param name
     * @return
     */
    BeanScopeEnum getScope(String name);

    /**
     * get the name of bean instance
     * @param name
     * @return
     */
    String getBeanName (String name);

    /**
     * get the bean's classloader.
     * @param name
     * @return
     */
    ClassLoader getClassLoader(String name);
}
