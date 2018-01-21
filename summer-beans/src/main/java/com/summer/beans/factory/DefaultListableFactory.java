package com.summer.beans.factory;

import com.summer.beans.bean.BeanDefinition;
import com.summer.beans.enums.BeanScopeEnum;
import com.summer.beans.exception.BeanNotFindException;
import com.summer.beans.exception.CircleReferenceException;
import com.summer.common.logger.CommonLogger;
import com.summer.common.support.Assert;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * default bean factory,the complete class which can holed the beans and create beans.
 * @author zys
 * @date 2018/01/15
 */
public class DefaultListableFactory extends AbstractBeanFactory {

    private Logger logger = CommonLogger.getLogger(DefaultListableFactory.class);
    private String logInfo = "default bean factory - ";

    /**
     * bean name -> attribute
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>(32);

    public DefaultListableFactory () {

        super();
    }

    public DefaultListableFactory (BeanFactory beanFactory) {

        super(beanFactory);
    }
    @Override
    public boolean containsBean(String name) {

        String canonicalName = getCanonicalName(name);
        return containsSingleton(canonicalName);
    }

    @Override
    public boolean isSingleton(String name) throws BeanNotFindException {

        String canonicalName = getCanonicalName(name);
        return containsSingleton(canonicalName);
    }

    @Override
    public boolean isPrototype(String name) throws BeanNotFindException {

        String canonicalName = getCanonicalName(name);
        return !containsSingleton(canonicalName);
    }

    @Override
    public Class<?> getType(String name) throws BeanNotFindException {

        Assert.hasText(name, "the bean's name can not be empty.");
        String canonicalName = getCanonicalName(name);
        if (beanDefinitionMap.containsKey(canonicalName)) {

            return this.beanDefinitionMap.get(canonicalName).getBeanClazz();
        } else {

            throw new BeanNotFindException("the bean '" + name + "' doesn't exist.");
        }
    }

    public void addBeanDefinitionMap (BeanDefinition beanDefinition) {

        Assert.notNull(beanDefinition, "bean definition can not be null while puting the definition into factory.");
        this.beanDefinitionMap.put(beanDefinition.getCanonicalName(), beanDefinition);
    }

    protected <T> T doGetBean(String name, @Nullable Class<T> clazz, @Nullable Object[] args) {

        //make the name is canonical name,not alias name
        String canonicalName = getCanonicalName(name);

        Object object = null;
        //first get the bean from sington registry
        if (containsSingleton(canonicalName)) {

            if (logger.isDebugEnabled()) {
                logger.debug(logInfo + "the singleton registry has this bean named - {}", canonicalName);
            }

            if (isCurrentInCreation(canonicalName)) {
                logger.debug(logInfo + "this singleton bean is creating.");
                //TODO expose the factory object,resolve circle dependency.
                return null;
            }

            object = getSingleton(canonicalName);
            //is singleton and haven't been created.
            if (object == null) {

                //start to create the singleton bean and put it in cache.
                BeanDefinition beanDefinition = this.beanDefinitionMap.get(canonicalName);
                if (beanDefinition == null) {

                    logger.warn("singleton bean definition not find,name - {}, canonical name - {}", name, canonicalName);
                    return null;
                }

                //create bean
                object = createBean(beanDefinition);

            }
            return (T) object;
        } else {
            //not a singleton object,check the circle dependency.
            if (prototypeInCreation(canonicalName)) {

                logger.error(logInfo + "the bean is prototype bean,occurred circle dependency.bean name - {}", canonicalName);
                throw new CircleReferenceException("the bean is prototype bean named '" +
                        canonicalName + "',occurred circle dependency");
            }

            //begin to create prototype bean

            BeanDefinition beanDefinition = this.beanDefinitionMap.get(canonicalName);
            if (beanDefinition == null) {

                logger.warn(logInfo + "bean definition not find,name - {}, canonical name - {}", name, canonicalName);
                return null;
            }

            //create bean
            object = createBean(beanDefinition);
            return (T) object;
        }

    }

    /**
     * create bean with bean definition.
     * @param beanDefinition
     * @return
     */
    protected Object createBean (BeanDefinition beanDefinition) {

        if (BeanScopeEnum.SINGLETON.equals(beanDefinition.getBeanScopeEnum())) {

            singletonCurrentlyInCreation(beanDefinition.getCanonicalName());
        } else if (BeanScopeEnum.PROTOTYPE.equals(beanDefinition.getBeanScopeEnum())) {

            prototypeInCreation(beanDefinition.getCanonicalName());
        }

        return doCreateBean(beanDefinition);
    }

    /**
     * create bean with given bean definition.
     * @param beanDefinition
     * @return
     */
    protected Object doCreateBean (BeanDefinition beanDefinition) {

        Class clazz = beanDefinition.getBeanClazz();
        Object returnObj = null;
        try {

            returnObj = clazz.newInstance();
            Map<String, Object> propertiesMap =beanDefinition.getPropertiesMap();

            for (Map.Entry<String, Object> entry : propertiesMap.entrySet()) {

                String propertyName = entry.getKey();
                Object propertyValue = entry.getValue();
                Field field = clazz.getDeclaredField(propertyName);
                Class fieldClass = field.getType();
                field.setAccessible(true);
                String type = fieldClass.getSimpleName();


                field.set(returnObj, switchType(type, propertyValue));
            }
        } catch (Exception e) {

            logger.error(logInfo + "occurs error while creating bean. - {}", e);
        }

        if (returnObj != null) {

            if (BeanScopeEnum.SINGLETON.equals(beanDefinition.getBeanScopeEnum())) {

                addSingleton(beanDefinition.getCanonicalName(), returnObj);
            }
        }
        return returnObj;
    }

    private Object switchType (String type, Object obj) {

        String value = (String) obj;
        Object result = null;
        switch (type) {
            case "Integer": case "int": {

                result = Integer.parseInt(value);
                break;
            }

            case "String" : {
                result = String.valueOf(value);
                break;
            }

            case "Short" : case "short" : {
                result = Short.parseShort(value);
                break;
            }

            case "Byte" : case "byte" : {
                result = Byte.parseByte(value);
                break;
            }

            case "Long" : case "long" : {
                result = Long.parseLong(value);
                break;
            }

            case "Char" : case "char" : {
                result = value.toCharArray();
                break;
            }

            default: {
                break;
            }
        }

        return result;
    }

    /**
     * destory Factory
     */
    public void destoryFactory() {

        super.destroySingleton();
        if (this.beanDefinitionMap !=null) {

            this.beanDefinitionMap.clear();
        }
    }

}
