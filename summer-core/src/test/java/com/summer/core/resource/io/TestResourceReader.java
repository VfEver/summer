package com.summer.core.resource.io;

import com.summer.common.logger.CommonLogger;
import com.summer.common.utils.StringUtils;
import com.summer.core.resource.parse.ResourceReader;
import junit.framework.TestCase;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TestResourceReader extends TestCase {

    private Logger logger = CommonLogger.getLogger(InputStreamResource.class);
    private String logInfo = "test ResourceReader - ";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetDocument() throws IOException {

        DefaultResource resource = new DefaultResource("src/test/resources/test-beans.xml") {
        };
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
