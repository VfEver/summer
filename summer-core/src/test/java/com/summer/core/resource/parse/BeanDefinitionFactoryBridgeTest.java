package com.summer.core.resource.parse;

import com.summer.beans.factory.BeanFactory;
import com.summer.beans.factory.DefaultListableFactory;
import com.summer.common.logger.CommonLogger;
import com.summer.core.resource.io.DefaultResource;
import junit.framework.TestCase;
import org.dom4j.Document;
import org.junit.Test;
import org.slf4j.Logger;

import java.io.IOException;

public class BeanDefinitionFactoryBridgeTest extends TestCase{

    private Logger logger = CommonLogger.getLogger(BeanDefinitionFactoryBridgeTest.class);
    private String logInfo = "test ResourceReader - ";

    DefaultListableFactory factory = null;
    BeanDefinitionFactoryBridge bridge = null;
    DefaultResource resource = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        factory = new DefaultListableFactory();
        bridge = new BeanDefinitionFactoryBridge(factory);
        resource = new DefaultResource("src/test/resources/test-beans.xml");
    }

    @Test
    public void testParse () throws IOException {

        ResourceReader resourceReader = new ResourceReader(resource);
        Document document = resourceReader.getDocument();
        bridge.parse(document);
        System.out.println(factory.containsBean("testBean"));
    }

    @Test
    public void testCreateBean () {

    }

    public void testAopRead () throws IOException {

        resource = new DefaultResource("src/test/resources/test-aop.xml");
        ResourceReader resourceReader = new ResourceReader(resource);
        Document document = resourceReader.getDocument();
        bridge.parse(document);

        BeanFactory beanFactory = factory;
        System.out.println("-----------");
    }

    public void testAop () {

    }
}
