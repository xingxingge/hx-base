package com.hx.base.xml;


public interface XmlDocumentFormatable {
    
    void importObjectFromFile(String fileName) throws XmlAccessException;
    
    void importObjectFromString(String xmlData) throws XmlAccessException;
    
    void exportObjectToFile(String fileName) throws XmlAccessException;
    
    String exportObjectToString() throws XmlAccessException; 
}
