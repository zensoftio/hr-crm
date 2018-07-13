package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Comment {
  private Integer id;
  private String text;
  @SerializedName("created_by")
  private User createdBy;
  private String created;
  @SerializedName("candidate")
  private Candidate candidate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }

}