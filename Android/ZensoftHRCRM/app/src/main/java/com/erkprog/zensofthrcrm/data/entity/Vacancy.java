package com.erkprog.zensofthrcrm.data.entity;

public class Vacancy {
  private Integer id;
  private String name;
  private String created;
  private String last_published;
  private String status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getLast_published() {
    return last_published;
  }

  public void setLast_published(String last_published) {
    this.last_published = last_published;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
