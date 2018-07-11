package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.os.Parcel;
import android.os.Parcelable;

public class SubTitle implements Parcelable {

  private String criteriaName;
  private Integer rate;
  private String comment;

  public String getCriteriaName() {
    return criteriaName;
  }

  public void setCriteriaName(String criteriaName) {
    this.criteriaName = criteriaName;
  }

  public SubTitle(String criteriaName, Integer rate) {
    this.criteriaName = criteriaName;
    this.rate = rate;
  }


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.criteriaName);
  }

  protected SubTitle(Parcel in) {
    this.criteriaName = in.readString();
    this.rate = in.readInt();
  }

  public static final Parcelable.Creator<SubTitle> CREATOR = new Parcelable.Creator<SubTitle>() {
    @Override
    public SubTitle createFromParcel(Parcel source) {
      return new SubTitle(source);
    }

    @Override
    public SubTitle[] newArray(int size) {
      return new SubTitle[size];
    }
  };

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}

