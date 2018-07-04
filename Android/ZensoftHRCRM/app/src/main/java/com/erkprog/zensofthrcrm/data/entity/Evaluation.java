package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

public class Evaluation {

  @SerializedName("id")
  private Integer id;
  @SerializedName("rating")
  private Integer rating;
  @SerializedName("criteria")
  private Criteria criteria;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public Criteria getCriteria() {
    return criteria;
  }

  public void setCriteria(Criteria criteria) {
    this.criteria = criteria;
  }
}
