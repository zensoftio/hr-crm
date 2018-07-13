package com.erkprog.zensofthrcrm.data.entity;

import java.util.List;

public class CandidateInterviewItem {

  private Integer id;
  private String status;
  private String date;
  private List<User> interviewers;

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

  public List<User> getInterviewers() {
    return interviewers;
  }

  public void setInterviewers(List<User> interviewers) {
    this.interviewers = interviewers;
  }

}
