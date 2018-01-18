package com.summer.beans.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * default bean definition.
 * @author zys
 * @date 2018/01/13
 */
public class BeanDefinition extends AbstractBeanDefinition{

    /**
     * the bean's properties.
     */
    private Map<String, Object> propertiesMap = new HashMap<>(4);

    public void addProperties(String name, Object value) {

        this.propertiesMap.put(name, value);
    }
}
