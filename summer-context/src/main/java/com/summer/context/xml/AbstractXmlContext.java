package com.summer.context.xml;

import com.summer.beans.exception.BeanNotFindException;
import com.summer.beans.factory.BeanFactory;
import com.summer.beans.factory.DefaultListableFactory;
import com.summer.core.resource.parse.BeanDefinitionFactoryBridge;
import com.summer.core.resource.parse.ResourceReader;
import org.dom4j.Document;

public abstract class AbstractXmlContext implements XmlContext, BeanFactory{

    private BeanFactory beanFactory;

    /**
     * load bean definition
     * @param resourceReader
     * @return
     */
    protected int loadBeanDefinition(ResourceReader resourceReader) {

        Document document = getDocument();
        BeanDefinitionFactoryBridge bridge = new BeanDefinitionFactoryBridge((DefaultListableFactory) beanFactory);
        bridge.parse(document);
        return 0;
    }

    protected BeanFactory obtainBeanFactory () {

        this.beanFactory = new DefaultListableFactory();
        return this.beanFactory;
    }

    /**
     * before init the factory.
     */
    protected void prepareLoad() {

        DefaultListableFactory curBeanFactory;
        if (beanFactory != null) {

            if (beanFactory instanceof DefaultListableFactory) {
                curBeanFactory = (DefaultListableFactory) beanFactory;
                curBeanFactory.destoryFactory();
            }
        }
    }

    @Override
    public Object getBean(String name) throws BeanNotFindException {

        return this.beanFactory.getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> tClass) throws BeanNotFindException {

        return this.beanFactory.getBean(name, tClass);
    }

    @Override
    public boolean containsBean(String name) {

        return this.beanFactory.containsBean(name);
    }

    @Override
    public boolean isSingleton(String name) throws BeanNotFindException {

        return this.beanFactory.isSingleton(name);
    }

    @Override
    public boolean isPrototype(String name) throws BeanNotFindException {

        return this.isPrototype(name);
    }

    @Override
    public Class<?> getType(String name) throws BeanNotFindException {

        return this.beanFactory.getType(name);
    }
}
