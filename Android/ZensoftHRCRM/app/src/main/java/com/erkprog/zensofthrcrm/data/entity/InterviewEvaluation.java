package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InterviewEvaluation {
  @SerializedName("user")
  private User user;
  @SerializedName("evaluations")
  private List<Evaluation> evaluationList = null;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Evaluation> getEvaluationList() {
    return evaluationList;
  }

  public void setEvaluationList(List<Evaluation> evaluationList) {
    this.evaluationList = evaluationList;
  }
}
