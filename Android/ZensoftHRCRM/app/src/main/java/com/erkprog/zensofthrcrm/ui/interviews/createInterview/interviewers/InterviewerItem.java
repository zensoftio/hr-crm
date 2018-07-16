package com.erkprog.zensofthrcrm.ui.interviews.createInterview.interviewers;

import com.erkprog.zensofthrcrm.data.entity.User;

public class InterviewerItem {

  private User user;
  private boolean checked;

  InterviewerItem(User user) {
    this.user = user;
    this.checked = false;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isChecked() {
    return checked;
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
  }
}
