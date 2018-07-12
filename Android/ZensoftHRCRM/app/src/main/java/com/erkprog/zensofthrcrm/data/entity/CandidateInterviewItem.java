package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CandidateInterviewItem {

  @SerializedName("id")
  private Integer id;
  @SerializedName("status")
  private String status;
  //  @SerializedName("date")
//  private Date date;
  @SerializedName("date")
  private String date;
  @SerializedName("interviewers")
  private List<User> interviewers = null;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  //  public Date getDate() {
//    return date;
//  }
//
//  public void setDate(Date date) {
//    this.date = date;
//  }

  public List<User> getInterviewers() {
    return interviewers;
  }

  public void setInterviewers(List<User> interviewers) {
    this.interviewers = interviewers;
  }

}
