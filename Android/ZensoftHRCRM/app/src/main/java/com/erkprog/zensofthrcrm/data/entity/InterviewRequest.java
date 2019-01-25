package com.erkprog.zensofthrcrm.data.entity;

import java.util.List;

public class InterviewRequest {
  private Integer candidate;
  private List<Integer> interviewers;
  private String date;


  public InterviewRequest(Integer candidate, List<Integer> interviewers, String date) {
    this.candidate = candidate;
    this.interviewers = interviewers;
    this.date = date;
  }

  public Integer getCandidate() {
    return candidate;
  }

  public void setCandidate(Integer candidate) {
    this.candidate = candidate;
  }

  public List<Integer> getInterviewers() {
    return interviewers;
  }

  public void setInterviewers(List<Integer> interviewers) {
    this.interviewers = interviewers;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
