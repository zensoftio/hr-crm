package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Interviewer {
  private Integer id;
  private User user;
  private String comment;

  @SerializedName("evaluations")
  private List<Evaluation> EvaluaionList;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public List<Evaluation> getEvaluaionList() {
    return EvaluaionList;
  }

  public void setEvaluaionList(List<Evaluation> evaluaionList) {
    EvaluaionList = evaluaionList;
  }

}
