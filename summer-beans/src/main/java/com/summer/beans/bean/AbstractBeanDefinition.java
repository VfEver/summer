package com.summer.beans.bean;

import com.summer.beans.enums.BeanScopeEnum;
import com.summer.common.support.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * the abstract class implements the bean's attributes interfaces.
 * just for sub-class.
 * @author zys
 * @date 2018/01/14
 */
public abstract class AbstractBeanDefinition implements BeanMetaAttribute, BeanAttribute{

    /**
     * the bean's class
     */
    private Class beanClazz;

    /**
     * the bean's classloader
     */
    private ClassLoader beanClassLoader;

    /**
     * the bean's instance name
     */
    private String instanceName;

    /**
     * the bean's canonical name.
     * eg:com.summer.beans.FactoryBean
     */
    private String canonicalName;

    /**
     * the bean attributes' map.
     * eg:
     * private String name;
     * 'name' -> String.class
     */
    private Map<String, Class> attributesMap = new HashMap<>(8);

    private BeanScopeEnum beanScopeEnum;

    public Class getBeanClazz() {
        return beanClazz;
    }

    public void setBeanClazz(Class beanClazz) {
        this.beanClazz = beanClazz;
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }

    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public BeanScopeEnum getBeanScopeEnum() {
        return beanScopeEnum;
    }
    public void setBeanScopeEnum(BeanScopeEnum beanScopeEnum) {
        this.beanScopeEnum = beanScopeEnum;
    }

    protected void addAttribute (String name, Class beanClazz) {

        Assert.hasText(name, "the bean's name must not be empty.");
        Assert.notNull(beanClazz, "the bean attributes' class must not be null.");

        this.attributesMap.put(name, beanClazz);
    }

    @Override
    public Class<?> getClass(String name) {

        return this.beanClazz;
    }

    @Override
    public ClassLoader getClassLoader(String name) {

        return this.beanClassLoader;
    }

    @Override
    public BeanScopeEnum getScope(String name) {

        return this.beanScopeEnum;
    }

    @Override
    public Class getDefinitionAttributes(String name) {

        return this.attributesMap.get(name);
    }
}
