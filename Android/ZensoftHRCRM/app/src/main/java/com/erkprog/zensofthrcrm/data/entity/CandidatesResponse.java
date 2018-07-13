package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CandidatesResponse {
  private Integer count;
  private String next;
  private String previous;
  @SerializedName("results")
  private List<Candidate> candidateList;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public String getPrevious() {
    return previous;
  }

  public void setPrevious(String previous) {
    this.previous = previous;
  }

  public List<Candidate> getCandidateList() {
    return candidateList;
  }

  public void setCandidateList(List<Candidate> candidateList) {
    this.candidateList = candidateList;
  }
}
