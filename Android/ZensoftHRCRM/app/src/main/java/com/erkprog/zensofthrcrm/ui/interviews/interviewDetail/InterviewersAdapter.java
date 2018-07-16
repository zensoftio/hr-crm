package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.SubTitle;
import com.erkprog.zensofthrcrm.data.entity.Title;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class InterviewersAdapter extends ExpandableRecyclerViewAdapter<InterviewersAdapter.InterviewerViewHolder, InterviewersAdapter.CriteriaViewHolder> {


  InterviewersAdapter(List<? extends ExpandableGroup> groups) {
    super(groups);
  }

  @Override
  public InterviewerViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.interviewer_item, parent, false);
    return new InterviewerViewHolder(view);
  }

  @Override
  public CriteriaViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.criteria_item, parent, false);
    return new CriteriaViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(CriteriaViewHolder holder, int flatPosition,
                                    ExpandableGroup group, int childIndex) {
    final SubTitle subTitle = ((Title) group).getItems().get(childIndex);
    if (subTitle != null) {
      holder.setCriteriaName(subTitle.getCriteriaName());
      holder.setRate(subTitle.getRate());
      holder.setComment(subTitle.getComment());
    }
  }

  @Override
  public void onBindGroupViewHolder(InterviewerViewHolder holder, int flatPosition, ExpandableGroup group) {
    if (group != null)
      holder.setInterviewerMail(group.getTitle());

  }


  class CriteriaViewHolder extends ChildViewHolder {

    private TextView criteriaName;
    private TextView rate;
    private TextView comment;

    CriteriaViewHolder(View itemView) {
      super(itemView);
      criteriaName = (TextView) itemView.findViewById(R.id.interview_interviewer_criteria);
      rate = (TextView) itemView.findViewById(R.id.rate);
      comment = (TextView) itemView.findViewById(R.id.interview_interviewer_comment);

    }

    void setCriteriaName(String criteriaName) {
      this.criteriaName.setText(criteriaName);
    }

    public void setRate(Integer rate) {
      this.rate.setText(String.valueOf(rate));
    }

    public void setComment(String comment) {
      this.comment.setText(comment);
    }
  }

  class InterviewerViewHolder extends GroupViewHolder {


    TextView interviewerMail;
    private ImageView arrow;


    InterviewerViewHolder(View itemView) {

      super(itemView);

      interviewerMail = itemView.findViewById(R.id.interviewer_mail);
      arrow = itemView.findViewById(R.id.arrow);


    }

    void setInterviewerMail(String interviewerMail) {
      this.interviewerMail.setText(interviewerMail);
    }
  }

}

