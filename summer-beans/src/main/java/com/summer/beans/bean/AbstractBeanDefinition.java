package com.summer.beans.bean;

import com.summer.beans.enums.BeanScopeEnum;
import com.summer.common.utils.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * the bean's class name
     */
    private String className;

    /**
     * the bean's canonical name.
     * means id
     */
    private String canonicalName;

    private Set<String> aliasNameSet = new HashSet<>(4);

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    @Override
    public void addAliasName(String aliasName) {

        this.aliasNameSet.add(aliasName);
    }

    public String[] getAliasName () {

        return StringUtils.toStringArray(this.aliasNameSet);
    }
}
