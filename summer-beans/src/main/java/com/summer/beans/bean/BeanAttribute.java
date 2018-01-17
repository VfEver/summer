package com.summer.beans.bean;

import com.summer.beans.enums.BeanScopeEnum;

import java.util.Map;

/**
 * bean's attributes
 * @author zys
 * @date 2018/01/07
 */
public interface BeanAttribute {

    /**
     * get the bean's declared attributes.
     * attribute name -> Class
     * @param name
     * @return
     */
    Class getDefinitionAttributes(String name);

    /**
     * add the alias name
     * @param aliasName
     */
    void addAliasName (String aliasName);
}
