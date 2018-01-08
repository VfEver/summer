package com.summer.core.resource.parse;

import com.summer.common.logger.CommonLogger;
import com.summer.core.resource.io.InputStreamResource;
import com.summer.core.resource.utils.SAXReaderUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * read the resource and parse the resource
 * @author zys
 * @date 2018/01/07
 */
public class ResourceReader {

    private Logger logger = CommonLogger.getLogger(ResourceReader.class);
    private String logInfo = "read the resource - ";
    /**
     * the resource to be read
     */
    private InputStreamResource inputStreamResource;

    public ResourceReader () {

        this(null);
    }

    public ResourceReader (InputStreamResource inputStreamResource) {

        this.inputStreamResource = inputStreamResource;
    }

    /**
     * return the Document by inputResource
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public Document getDocument() throws IOException {

        if (inputStreamResource == null || !inputStreamResource.isExist()) {

            logger.warn(logInfo + "the resource is null or doesn't exist.");
            return null;
        }

        logger.info(logInfo + "use dom4j and get the SAXReader to read resource.");
        SAXReader saxReader = SAXReaderUtils.getSaxReader();

        Document document = null;

        try {
            logger.info(logInfo + "begin read the resource.");
            document = saxReader.read(inputStreamResource.getInputStream());
        } catch (DocumentException e) {

            logger.error(logInfo + "saxReader has probles with parsing the resource.", e);
        }

        return document;
    }
}
