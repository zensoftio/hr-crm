package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Candidate {

  @SerializedName("id")
  private Integer id;
  @SerializedName("first_name")
  private String firstName;
  @SerializedName("last_name")
  private String lastName;
  @SerializedName("email")
  private String email;
  @SerializedName("position")
  private Position position = null;
  @SerializedName("experience")
  private Float experience;
  @SerializedName("level")
  private String level;
  @SerializedName("created")
  private String created;
  @SerializedName("skype")
  private String skype;
  @SerializedName("status")
  private String status;
  @SerializedName("phone")
  private String phone;
  @SerializedName("cvs")
  private List<Cv> cvs = null;
  @SerializedName("interviews")
  private List<Interview> interviews = null;
  @SerializedName("comments")
  private List<Comment> comments = null;

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Integer getId() {
    return id;
  }

  public String getSkype() {
    return skype;
  }

  public void setSkype(String skype) {
    this.skype = skype;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public Float getExperience() {
    return experience;
  }

  public void setExperience(Float experience) {
    this.experience = experience;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Cv> getCvs() {
    return cvs;
  }

  public void setCvs(List<Cv> cvs) {
    this.cvs = cvs;
  }

  public List<Interview> getInterviews() {
    return interviews;
  }

  public void setInterviews(List<Interview> interviews) {
    this.interviews = interviews;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
}
