package com.erkprog.zensofthrcrm.data.entity;

public class Position {
  private Integer id;
  private String name;
  private Department department;

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

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department departmentModel) {
    this.department = departmentModel;
  }
}
