package com.hx.base.util.xml;


import com.hx.base.interfaces.StreamFormatable;
import com.hx.base.io.DefaultResourceLoader;
import com.hx.base.io.Resource;
import com.hx.base.io.ResourceLoader;
import com.hx.base.xml.XmlAccessException;
import com.hx.base.xml.XmlDocumentFormatable;
import com.hx.base.xml.XmlElementFormatable;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DefaultDocumentFormatter implements XmlDocumentFormatable,
        StreamFormatable {
  private static final Logger logger = LoggerFactory
          .getLogger(DefaultDocumentFormatter.class);
  private XmlElementFormatable formater;
  private String encoding = "UTF-8";

  public void exportObjectToFile(String fileName) throws XmlAccessException {
    if (logger.isDebugEnabled())
      logger.debug("Export object '{}' to file '{}'!", formater.getClass()
              .getCanonicalName(), fileName);
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource(fileName);
    OutputStream fos = null;
    try {
      fos = resource.getOutputStream();
      exportObject(fos);
      logger.debug("Export object successed!");
    } catch (XmlAccessException e) {
      throw e;
    } catch (Exception e) {
      throw new XmlAccessException(e);
    } finally {
      try {
        if (fos != null)
          fos.close();
      } catch (IOException e) {
        throw new XmlAccessException(e);
      }
    }
    logger.debug("Export object successed!");
  }

  public String exportObjectToString() throws XmlAccessException {
    if (logger.isDebugEnabled())
      logger.debug("Export object '{}'!", formater.getClass()
              .getCanonicalName());
    Element element = formater.exportObjectToElement(null);
    Document document = element.getDocument();
    if (encoding != null)
      document.setXMLEncoding(encoding);
    return document.asXML();
  }

  public void exportObject(OutputStream os) throws XmlAccessException,
          IOException {
    OutputFormat format = OutputFormat.createPrettyPrint();
    if (encoding != null)
      format.setEncoding(encoding);
    XMLWriter writer = null;
    try {
      writer = new XMLWriter(os, format);
      Element element = formater.exportObjectToElement(null);
      writer.write(element.getDocument());
    } finally {
      try {
        if (writer != null)
          writer.close();
      } catch (IOException e) {
        throw new XmlAccessException(e);
      }
    }
  }

  public void importObjectFromFile(String fileName) throws XmlAccessException {
    if (logger.isDebugEnabled())
      logger.debug("Import object '{}' from file '{}'!", formater.getClass()
              .getCanonicalName(), fileName);
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource(fileName);
    InputStream fis = null;
    try {
      fis = resource.getInputStream();
      importObject(fis);
    } catch (Exception e) {
      throw new XmlAccessException(e);
    } finally {
      try {
        if (fis != null)
          fis.close();
      } catch (IOException e) {
        throw new XmlAccessException(e);
      }
    }
    logger.debug("Import object successed!");
  }

  public void importObjectFromString(String xmlData) throws XmlAccessException {
    if (logger.isDebugEnabled())
      logger.debug("Import object '{}' from string!", formater.getClass()
              .getCanonicalName());
    ByteArrayInputStream bis = null;
    try {
      bis = new ByteArrayInputStream(xmlData.getBytes(encoding));
    } catch (Exception e) {
      throw new XmlAccessException(e.getMessage(), e);
    }
    try {
      importObject(bis);
    } catch (Exception e) {
      throw new XmlAccessException(e);
    } finally {
      try {
        bis.close();
      } catch (IOException e) {
        throw new XmlAccessException(e);
      }
    }
    logger.debug("Import object successed!");
  }

  public void importObject(InputStream is) throws XmlAccessException {
    SAXReader reader = new SAXReader();
    if (encoding != null)
      reader.setEncoding(encoding);
    Document document;
    try {
      document = reader.read(is);
    } catch (DocumentException e) {
      throw new XmlAccessException("Read failed!", e);
    }
    formater.importObjectFromElement(document.getRootElement());
  }

  /**
   * @return the formater
   */
  public XmlElementFormatable getFormater() {
    return formater;
  }

  /**
   * @param formater the formater to set
   */
  public void setFormater(XmlElementFormatable formater) {
    this.formater = formater;
  }

  /**
   * @return the encoding
   */
  public String getEncoding() {
    return encoding;
  }

  /**
   * @param encoding the encoding to set
   */
  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

}
