package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Request {
  @SerializedName("id")
  private Integer id;
  @SerializedName("name")
  private String name;
  @SerializedName("created")
  private String created;
  @SerializedName("modified")
  private String modified;
  @SerializedName("userCreatedBy")
  private User userCreatedBy;
  @SerializedName("department")
  private Department department;
  @SerializedName("count")
  private Integer count;
  @SerializedName("position")
  private Position position;
  @SerializedName("status")
  private Integer status;
  @SerializedName("requirements")
  private List<Requirement> requirementList = null;

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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public List<Requirement> getRequirementList() {
    return requirementList;
  }

  public void setRequirementList(List<Requirement> requirementList) {
    this.requirementList = requirementList;
  }
}
