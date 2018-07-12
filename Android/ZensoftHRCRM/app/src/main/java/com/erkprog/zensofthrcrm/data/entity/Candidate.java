package com.erkprog.zensofthrcrm.data.entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Candidate {

  private Integer id;
  @SerializedName("first_name")
  private String firstName;
  @SerializedName("last_name")
  private String lastName;
  private String email;
  private Position position;
  private Float experience;
  private String level;
  private String status;
  private String phone;
  private String created;
  private String skype;
  @SerializedName("CVs")
  private List<Cv> cvs;
  private List<CandidateInterviewItem> interviews;
  private List<Comment> comments;

  public Integer getId() {
    return id;
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

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
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

  public List<CandidateInterviewItem> getInterviews() {
    return interviews;
  }

  public void setInterviews(List<CandidateInterviewItem> interviews) {
    this.interviews = interviews;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }
}
