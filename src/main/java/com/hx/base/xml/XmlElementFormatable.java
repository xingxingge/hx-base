package com.hx.base.xml;

import org.dom4j.Element;

public interface XmlElementFormatable {
    
    boolean canImport(Element element);
    
    String[] getSupportElementNames();
    
    void importObjectFromElement(Element element) throws XmlAccessException;
    /**
     * 将对象内容持久化到XML文件中
     * @param element 对象要持久化到的xml节点，当该值为null时，内部对象
     * 创建一个根节点。
     * @throws XmlAccessException
     */
    Element exportObjectToElement(Element element) throws XmlAccessException;
}
