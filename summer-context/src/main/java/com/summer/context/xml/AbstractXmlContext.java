package com.summer.context.xml;

import com.summer.beans.factory.BeanFactory;
import com.summer.beans.factory.DefaultListableFactory;
import com.summer.core.resource.parse.ResourceReader;
import org.dom4j.Document;

public abstract class AbstractXmlContext implements XmlContext {

    private BeanFactory beanFactory;

    /**
     * load bean definition
     * @param resourceReader
     * @return
     */
    protected int loadBeanDefinition(ResourceReader resourceReader) {

        Document document = getDocument();

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
}
