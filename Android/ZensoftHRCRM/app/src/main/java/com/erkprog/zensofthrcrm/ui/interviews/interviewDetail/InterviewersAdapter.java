package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erkprog.zensofthrcrm.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class InterviewersAdapter extends ExpandableRecyclerViewAdapter<InterviewerViewHolder, CriteriaViewHolder> {

  private Context context;

  public InterviewersAdapter(Context context, List<? extends ExpandableGroup> groups) {
    super(groups);
    this.context = context;
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
    if (subTitle.getCriteriaName() != null)
      holder.setCriteriaName(subTitle.getCriteriaName());
    if (subTitle.getRate() != null)
      holder.setRate(subTitle.getRate());
    if (subTitle.getComment() != null)
      holder.setComment(subTitle.getComment());
  }

  @Override
  public void onBindGroupViewHolder(InterviewerViewHolder holder, int flatPosition, ExpandableGroup group) {
    if (group.getTitle() != null)
      holder.setInterviewerMail(group.getTitle());

  }
}