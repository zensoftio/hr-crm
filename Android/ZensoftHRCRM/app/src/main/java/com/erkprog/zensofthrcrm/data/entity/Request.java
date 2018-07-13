package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Request {
  private Integer id;
  private String created;
  private String modified;
  private User userCreatedBy;
  private Department department;
  private Integer count;
  private Position position;
  private String status;
  @SerializedName("requirements")
  private List<Requirement> requirementList = null;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getModified() {
    return modified;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

  public User getUserCreatedBy() {
    return userCreatedBy;
  }

  public void setUserCreatedBy(User userCreatedBy) {
    this.userCreatedBy = userCreatedBy;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Requirement> getRequirementList() {
    return requirementList;
  }

  public void setRequirementList(List<Requirement> requirementList) {
    this.requirementList = requirementList;
  }
}
