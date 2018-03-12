package com.summer.core.resource.parse;

import com.summer.aop.advice.Advice;
import com.summer.aop.advice.DefaultAdvice;
import com.summer.aop.aspect.Aspect;
import com.summer.aop.aspect.DefaultAspect;
import com.summer.aop.pointcut.DefaultPointcut;
import com.summer.aop.pointcut.Pointcut;
import com.summer.beans.bean.BeanDefinition;
import com.summer.beans.enums.BeanScopeEnum;
import com.summer.beans.exception.SymbolNotDefinedException;
import com.summer.beans.factory.DefaultListableFactory;
import com.summer.common.support.Assert;
import com.summer.common.utils.ClassUtils;
import com.summer.common.utils.StringUtils;
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
    private final static String REF = "ref";
    private final static String AOP_CONFIG = "aop-config";
    private final static String AOP_POINTCUT = "aop-pointcut";
    private final static String AOP_ASPECT = "aop-aspect";
    private final static String AOP_BEFORE = "aop-before";
    private final static String AOP_AFTER = "aop-after";
    private final static String METHOD = "method";
    private final static String POINTCUT_REF = "pointcut_ref";
    private final static String EXPRESSION = "expression";
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

            case AOP_CONFIG : {

                initAspect(element);
                break;
            }
            default: {

                String errorMsg = "the symbol - " + element.getName() + "can not be parse,please make sure has the correct spelling.";
                throw new SymbolNotDefinedException(errorMsg);
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

                    String errorMsg = "the symbol - " + element.getName() + "can not be parse,please make sure has the correct spelling.";
                    throw new SymbolNotDefinedException(errorMsg);
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

        String name = element.attributeValue(NAME);
        String value = element.attributeValue(VALUE);
        String refObj = element.attributeValue(REF);

        Assert.hasText(name, "the bean's property name must not be empty.");
        if (StringUtils.isEmpty(value) && StringUtils.isEmpty(refObj)) {

            Assert.hasText("", "the bean's property value or ref must not be empty.");
        }

        if (StringUtils.isNotEmpty(value)) {

            beanDefinition.addProperties(name, value);
        } else {

            beanDefinition.addProperties(name, refObj);
        }
    }

    private void initAspect (Element element) {

        Iterator<Element> iterator = element.elementIterator();
        DefaultAspect aspect = new DefaultAspect();

        while (iterator.hasNext()) {

            Element childNode = iterator.next();
            String elementName = childNode.getName();

            switch (elementName) {

                case AOP_POINTCUT : {

                    DefaultPointcut pointcut = new DefaultPointcut();
                    String expression = childNode.attributeValue(EXPRESSION);
                    pointcut.setExpression(expression);
                    String poiontcutName = childNode.attributeValue(ID);
                    pointcut.setPointcutName(poiontcutName);
                    aspect.setPointcut(pointcut);
                }

                case AOP_ASPECT : {

                    Advice advice = initAdvicor(childNode);
                    aspect.setAdvice(advice);
                }
            }
        }
    }

    private Advice initAdvicor(Element element) {

        DefaultAdvice advice = new DefaultAdvice();
        String advicorName = element.attributeValue(ID);
        String advicorObject = element.attributeValue(REF);
        advice.setAdvicor(advicorObject);
        advice.setAdvicorName(advicorName);
        return advice;
    }
}
