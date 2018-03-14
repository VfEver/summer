package com.summer.core.resource.aspect;

import com.summer.core.resource.io.DefaultResource;
import com.summer.core.resource.parse.ResourceReader;
import junit.framework.TestCase;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AspectTest extends TestCase {

    private Logger logger = LoggerFactory.getLogger(AspectTest.class);
    private String logInfo = "aop test - ";
    @Test
    public void testReadXml() throws IOException {

        DefaultResource resource = new DefaultResource("src/test/resources/test-aop.xml");
        ResourceReader resourceReader = new ResourceReader(resource);

        Document document = resourceReader.getDocument();
        Element rootElement = document.getRootElement();

        listNodes(rootElement);
    }

    public void listNodes(Element node){
        logger.info(logInfo + "current node name：" + node.getName());
        //get the current node attribute
        List<Attribute> list = node.attributes();

        for(Attribute attribute : list){

            logger.info(logInfo + "attribute - "+attribute.getName() +":" + attribute.getValue());
        }
        //if not null
        if(!(node.getTextTrim().equals(""))){
            logger.info(logInfo + node.getName() + "：" + node.getText());
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            if (null != e.attribute("class")) {
                System.out.println("-----------------" + e.attribute("class").getValue());
            }
            listNodes(e);
        }
    }
}
