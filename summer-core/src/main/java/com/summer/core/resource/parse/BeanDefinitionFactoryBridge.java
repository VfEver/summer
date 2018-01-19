package com.summer.core.resource.parse;

import com.summer.beans.bean.BeanDefinition;
import com.summer.beans.enums.BeanScopeEnum;
import com.summer.beans.factory.DefaultListableFactory;
import com.summer.common.support.Assert;
import com.summer.common.utils.ClassUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

/**
 * make a connection by bean definition and bean factory
 * @author zys
 * @date 2018/01/16
 */
public class BeanDefinitionFactoryBridge {

    private final static String BEANS = "beans";
    private final static String BEAN = "bean";
    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String PROPERTY = "property";
    private final static String VALUE = "value";
    private final static String IMPORT = "import";
    private final static String CLASS = "class";
    private final static String SCOPE = "scope";

    private static DefaultListableFactory beanFactory;

    public BeanDefinitionFactoryBridge () {

    }

    public BeanDefinitionFactoryBridge (DefaultListableFactory defaultListableFactory) {

        this.beanFactory = defaultListableFactory;
    }

    /**
     * parse the document with beanFactory which given at constructor.
     * @param document
     */
    public void parse (Document document) {

        parse(document, beanFactory);
    }

    /**
     * parse the document with beanFactory as given.
     * @param document
     * @param beanFactory
     */
    public void parse (Document document, DefaultListableFactory beanFactory) {

        Assert.notNull(document, "the xml document can not be null.");
        Assert.notNull(beanFactory, "the factory can not be null.");

        Element rootElement = document.getRootElement();
        recursionParse(rootElement);
    }

    private void recursionParse (Element element) {

        switch (element.getName()) {

            case BEANS : {

                Iterator<Element> iterator = element.elementIterator();
                while(iterator.hasNext()){
                    Element e = iterator.next();
                    recursionParse(e);
                }
                break;
            }
            case BEAN : {

                initBeanDefinition(element);
                break;
            }

            case IMPORT : {

                break;
            }
            default: {

                break;
            }
        }
    }

    /**
     * init the bean
     * @param element
     */
    private void initBeanDefinition (Element element) {

        BeanDefinition beanDefinition = new BeanDefinition();
        List<Attribute> attributes = element.attributes();
        for (Attribute attribute : attributes) {

            switch (attribute.getName()) {
                case ID: {

                    String id = element.attributeValue(ID);
                    beanDefinition.setCanonicalName(id);
                    break;
                }
                case CLASS : {

                    String className = element.attributeValue(CLASS);
                    beanDefinition.setClassName(className);
                    Class clazz = ClassUtils.getClassByClassName(className);
                    beanDefinition.setBeanClazz(clazz);
                    ClassLoader classLoader = ClassUtils.getClassLoader(clazz);
                    break;
                }
                case NAME : {

                    String[] names = element.attributeValue(NAME).split(",");
                    for (String aliasName : names) {

                        Assert.hasText(aliasName, "the bean's alias name must not be empty.");
                        beanDefinition.addAliasName(aliasName);
                    }
                    break;
                }

                case SCOPE : {

                    String scope = element.attributeValue(SCOPE);
                    BeanScopeEnum beanScopeEnum = BeanScopeEnum.of(scope);
                    beanDefinition.setBeanScopeEnum(beanScopeEnum);
                    break;
                }
                default: {

                    break;
                }
            }
        }

        if (beanDefinition.getBeanScopeEnum() == null) {

            beanDefinition.setBeanScopeEnum(BeanScopeEnum.SINGLETON);
        }

        //register alias
        registerAlias(beanDefinition);

        //the inner property.
        Iterator<Element> iterator = element.elementIterator();
        while (iterator.hasNext()) {

            beanInnerInit(iterator.next(), beanDefinition);
        }

        beanFactory.addBeanDefinitionMap(beanDefinition);

        if (beanDefinition.getBeanScopeEnum().getScope().equals(BeanScopeEnum.SINGLETON.getScope())) {
            beanFactory.addSingletonSet(beanDefinition.getCanonicalName());
        }
    }


    /**
     * register the bean's alias
     * @param beanDefinition
     */
    private void registerAlias (BeanDefinition beanDefinition) {

        if (beanDefinition.getAliasName() == null ||
               beanDefinition.getAliasName().length <= 0) {
            return ;
        }

        String[] aliasNames = beanDefinition.getAliasName();
        String canonicalName = beanDefinition.getCanonicalName();
        for (String aliasName : aliasNames) {

            beanFactory.registryAlias(canonicalName, aliasName);
        }
    }

    /**
     * parse the bean property.
     * @param element
     * @param beanDefinition
     */
    private void beanInnerInit (Element element, BeanDefinition beanDefinition) {

        List<Attribute> attributes = element.attributes();
        String name = element.attributeValue(NAME);
        String value = element.attributeValue(VALUE);
        //TODO ref should be added
        Assert.hasText(name, "the bean's property name must not be empty.");
        Assert.hasText(value, "the bean's property value must not be empty.");

        beanDefinition.addProperties(name, value);
    }
}
