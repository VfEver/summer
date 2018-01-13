package com.summer.beans;

import com.summer.beans.bean.BeanPlaceHolder;
import com.summer.beans.enums.BeanScopeEnum;

/**
 * abstract class implements BeanPlaceHolder.
 */
public abstract class AbstractBeanPlaceHolder implements BeanPlaceHolder {

    /**
     * the bean's id
     */
    private String id;

    /**
     * the bean's scope
     */
    private BeanScopeEnum scope;

    @Override
    public String getId() {

        return id;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public BeanScopeEnum getScope() {

        return scope;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setScope(BeanScopeEnum scope) {

        this.scope = scope;
    }
}
