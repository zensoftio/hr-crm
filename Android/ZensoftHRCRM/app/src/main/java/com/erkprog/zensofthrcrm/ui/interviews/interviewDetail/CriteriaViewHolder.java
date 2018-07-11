package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.view.View;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class CriteriaViewHolder extends ChildViewHolder {

  private TextView criteriaName;
  private TextView rate;
  private TextView comment;

  public CriteriaViewHolder(View itemView) {
    super(itemView);
    criteriaName = (TextView) itemView.findViewById(R.id.interview_interviewer_criteria);
    rate = (TextView) itemView.findViewById(R.id.rate);
    comment = (TextView) itemView.findViewById(R.id.interview_interviewer_comment);

  }

  public void setCriteriaName(String criteriaName) {
    this.criteriaName.setText(criteriaName);
  }

  public void setRate(Integer rate) {
    this.rate.setText(rate.toString());
  }

  public void setComment(String comment) {
    this.comment.setText(comment);
  }
}