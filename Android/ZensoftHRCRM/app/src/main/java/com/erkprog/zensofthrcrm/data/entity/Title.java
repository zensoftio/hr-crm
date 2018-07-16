package com.erkprog.zensofthrcrm.data.entity;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;


public class Title extends ExpandableGroup<SubTitle> {

  private String email;

  public Title(String email, List<SubTitle> items) {
    super(email, items);
    this.email = email;
  }

  @Override
  public String getTitle() {
    return super.getTitle();
  }

  public String getEmail(String email) {
    return email;
  }
}

