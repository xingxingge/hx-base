package com.hx.base.xml;

import com.hx.base.util.string.StringFormater;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public abstract class AbstractElementFormater implements XmlElementFormatable {
  protected String[] supportElementNames = new String[0];

  public boolean canImport(Element element) {
    for (int i = 0; i < supportElementNames.length; i++) {
      if (supportElementNames[i].equals(element.getName()))
        return true;
    }
    return false;
  }

  protected Element createRootElement(Element element, String rootName) {
    Element elRoot = null;
    if (element != null) {
      elRoot = element.addElement(rootName);
    } else {
      Document doc = DocumentHelper.createDocument();
      elRoot = doc.addElement(rootName);
    }
    return elRoot;
  }

  public String[] getSupportElementNames() {
    return supportElementNames;
  }

  protected String getAttribute(Element element, String attribute, boolean option) throws XmlAccessException {
    Attribute attr = (Attribute) element.attribute(attribute);
    if (attr != null) {
      return attr.getValue();
    }
    if (option)
      return null;
    throw new XmlAccessException(StringFormater.format("There is no attribute '{}' in element '{}'!", attribute, element.getName()));
  }

  protected String getElementText(Element element, String textElement, boolean option) throws XmlAccessException {
    Element el = (Element) element.element(textElement);
    if (el != null) {
      return el.getTextTrim();
    }
    if (option)
      return null;
    throw new XmlAccessException(StringFormater.format("There is no element '{}' in element '{}'!", textElement, element.getName()));
  }
}
