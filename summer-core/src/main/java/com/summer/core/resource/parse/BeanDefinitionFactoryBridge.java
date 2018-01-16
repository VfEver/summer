package com.summer.core.resource.parse;

import com.summer.beans.factory.DefaultListableFactory;
import org.dom4j.Document;

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
    public static void parse (Document document) {

        parse(document, beanFactory);
    }

    /**
     * parse the document with beanFactory as given.
     * @param document
     * @param beanFactory
     */
    public static void parse (Document document, DefaultListableFactory beanFactory) {


    }

}
