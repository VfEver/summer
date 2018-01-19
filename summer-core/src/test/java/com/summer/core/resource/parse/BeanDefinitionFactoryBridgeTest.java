package com.summer.core.resource.parse;

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

    @Test
    public void testParse () throws IOException {

        DefaultListableFactory factory = new DefaultListableFactory();
        BeanDefinitionFactoryBridge bridge = new BeanDefinitionFactoryBridge(factory);

        DefaultResource resource = new DefaultResource("src/test/resources/test-beans.xml");
        ResourceReader resourceReader = new ResourceReader(resource);
        Document document = resourceReader.getDocument();
        bridge.parse(document);
        System.out.println(factory.containsBean("testBean"));
    }
}
