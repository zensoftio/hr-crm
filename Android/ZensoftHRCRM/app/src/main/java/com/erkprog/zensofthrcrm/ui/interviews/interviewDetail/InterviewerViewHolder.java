package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;


public class InterviewerViewHolder extends GroupViewHolder {


  TextView interviewerMail;
  private ImageView arrow;


  public InterviewerViewHolder(View itemView) {

    super(itemView);

    interviewerMail = itemView.findViewById(R.id.interviewer_mail);
    arrow = itemView.findViewById(R.id.arrow);


  }

  public void setInterviewerMail(String interviewerMail) {
    this.interviewerMail.setText(interviewerMail);
  }
}