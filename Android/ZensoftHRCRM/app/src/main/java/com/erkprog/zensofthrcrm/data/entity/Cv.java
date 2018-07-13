package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

public class Cv {
  @SerializedName("id")
  private Integer id;

  @SerializedName("url")
  private String link;

//    @SerializedName("created")
//    private Date created;

  @SerializedName("created")
  private String created;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  //    public Date getCreated() {
//        return created;
//    }
//
//    public void setCreated(Date created) {
//        this.created = created;
//    }
}
