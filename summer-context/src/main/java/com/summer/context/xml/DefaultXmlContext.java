package com.summer.context.xml;

import com.summer.common.logger.CommonLogger;
import com.summer.core.resource.parse.ResourceReader;
import org.dom4j.Document;
import org.slf4j.Logger;

import java.io.IOException;

public class DefaultXmlContext extends AbstractXmlContext implements XmlContext {

    private Logger logger = CommonLogger.getLogger(DefaultXmlContext.class);
    private String logInfo = "use default xml context as application context - ";
    /**
     * the resource reader
     */
    private ResourceReader resourceReader;

    public void setResourceReader(ResourceReader resourceReader) {
        this.resourceReader = resourceReader;
    }

    public DefaultXmlContext () {
    }
    public DefaultXmlContext (String filePath) {
        this.resourceReader = new ResourceReader(filePath);
    }

    @Override
    public Document getDocument() {

        Document document = null;
        try {

            document = this.resourceReader.getDocument();
        } catch (IOException e) {

            logger.error(logInfo + "occurs exception while loading xml file - ", e);
        }

        return document;
    }

    public void onFresh() {

        if (resourceReader != null) {

            //prepare the application context.
            prepareLoad();
            //initialize the bean factory
            obtainBeanFactory();

            int count = loadBeanDefinition(resourceReader);
        }
    }
}
