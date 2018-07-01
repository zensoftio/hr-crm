package com.erkprog.zensofthrcrm.data.entity;

import java.util.Date;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CandidateInterviewItem {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("status")
  @Expose
  private Integer status;
  @SerializedName("date")
  @Expose
  private Date date;
  @SerializedName("interviewers")
  @Expose
  private List<User> interviewers = null;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public List<User> getInterviewers() {
    return interviewers;
  }

  public void setInterviewers(List<User> interviewers) {
    this.interviewers = interviewers;
  }

}
