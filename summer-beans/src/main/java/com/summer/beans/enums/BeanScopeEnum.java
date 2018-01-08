package com.summer.beans.enums;

/**
 * bean's scope enum
 * @author zys
 * @date 2018/01/07
 */
public enum BeanScopeEnum {

    /**
     * singleton class,it means only one instance in global.
     */
    SINGLETON("singleton"),

    /**
     * prototype class,it means every calls will make a new instance.
     */
    PROTOTYPE("prototype"),
    ;

    private String scope;
    BeanScopeEnum(String scope) {

        this.scope = scope;
    }
}
