package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PositionsResponse {
  @SerializedName("count")
  private Integer count;
  @SerializedName("next")
  private String next;
  @SerializedName("previous")
  private String previous;
  @SerializedName("positions")
  private List<Position> positionList = null;

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

  public List<Position> getPositionList() {
    return positionList;
  }

  public void setPositionList(List<Position> positionList) {
    this.positionList = positionList;
  }
}
