package com.hx.base.persistence;

public interface ResourceAware extends LifeAware {
    
    String getId();
    
    void setId(String id);
    
    String getName();
    
    void setName(String name);
    
    String getAlias();
    
    void setAlias(String alias);
    
    int getOrdinal();
    
    void setOrdinal(int ordinal);
    
    String getKeywords();
    
    void setKeywords(String keywords);
    
    String getUri();
    
    void setUri(String uri);
    
    String getResourceType();
    
    void setResourceType(String resourceType);
    
    boolean isLocked();
    
    void setLocked(boolean locked);
    
    String getOriginal();
    
    void setOriginal(String reference);
    
    String getExternalId();
    
    void setExternalId(String externalId);
    
    String getNote();
    
    void setNote(String note);
}
