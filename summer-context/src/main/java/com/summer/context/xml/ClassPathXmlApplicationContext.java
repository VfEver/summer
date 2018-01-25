package com.summer.context.xml;

import com.summer.common.logger.CommonLogger;
import com.summer.core.resource.parse.ResourceReader;
import org.slf4j.Logger;

import java.net.URL;

/**
 * read the xml document which located in class path.
 * @author zys
 * @date 2018/01/25
 */
public class ClassPathXmlApplicationContext extends DefaultXmlContext {

    private Logger logger = CommonLogger.getLogger(ClassPathXmlApplicationContext.class);
    private String logInfo = "load the classpath xml - ";

    public ClassPathXmlApplicationContext () {
        super();
    }

    public ClassPathXmlApplicationContext (String fileName) {

        URL url = this.getClass().getClassLoader().getResource(fileName);
        String realFilePath = url.getPath();

        logger.info(logInfo + "the xml path is {}", realFilePath);
        ResourceReader resourceReader = new ResourceReader(realFilePath);
        setResourceReader(resourceReader);

        logger.info(logInfo + "fresh the context and begin to parse.");
        onFresh();
    }
}
