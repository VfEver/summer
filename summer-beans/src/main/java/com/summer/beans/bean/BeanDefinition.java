package com.summer.beans.bean;

/**
 * @author zys
 * @date 2018/01/13
 */
public class BeanDefinition extends AbstractBeanDefinition{


    @Override
    public String getBeanName(String name) {
        return this.getInstanceName();
    }

}
