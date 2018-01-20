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
    public String getScope () {

        return this.scope;
    }
    BeanScopeEnum(String scope) {

        this.scope = scope;
    }
    public static BeanScopeEnum of (String scope) {

        for (BeanScopeEnum scopeEnum : BeanScopeEnum.values()) {

            if (scopeEnum.getScope().equals(scope)) {
                return scopeEnum;
            }
        }
        return null;
    }

    public boolean equals (BeanScopeEnum beanScopeEnum) {

        if (beanScopeEnum != null) {

            return this.getScope().equals(beanScopeEnum.getScope());
        }
        return false;
    }
}
