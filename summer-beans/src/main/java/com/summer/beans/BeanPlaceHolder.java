package com.summer.beans;

import com.summer.beans.enums.BeanScopeEnum;

/**
 * bean's attibutes holder interface,including scope name id and soon
 * @author zys
 * @date 2018/01/07
 */
public interface BeanPlaceHolder extends Bean {

    /**
     * get the scope of the bean.
     * @return
     */
    BeanScopeEnum getScope();

    /**
     * get the name of the bean
     * @return
     */
    String getName();

}
