package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Evaluation {

  private Integer id;
  private Integer rate;
  private Criteria criteria;

  public Criteria getCriteria() {
    return criteria;
  }

  public void setCriteria(Criteria criteria) {
    this.criteria = criteria;
  }

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

}
