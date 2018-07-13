package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CriteriasResponse {

  private Integer count;
  private Object next;
  private Object previous;
  @SerializedName("results")
  private List<Criteria> criteriaList;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Object getNext() {
    return next;
  }

  public void setNext(Object next) {
    this.next = next;
  }

  public Object getPrevious() {
    return previous;
  }

  public void setPrevious(Object previous) {
    this.previous = previous;
  }

  public List<Criteria> getCriteriaList() {
    return criteriaList;
  }

  public void setCriteriaList(List<Criteria> criteriaList) {
    this.criteriaList = criteriaList;
  }

}