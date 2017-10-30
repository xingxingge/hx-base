package com.hx.base.persistence;

import java.util.Date;

public interface LifeAware {

  Date getCreatedTime();

  void setCreatedTime(Date createdTime);

  Date getLastModifiedTime();

  void setLastModifiedTime(Date lastModifiedTime);

  Date getDeletedTime();

  void setDeletedTime(Date deletedTime);

  boolean isDeleted();

  String getCreator();

  void setCreator(String creator);

  String getLastModifier();

  void setLastModifier(String lastModifier);

}
