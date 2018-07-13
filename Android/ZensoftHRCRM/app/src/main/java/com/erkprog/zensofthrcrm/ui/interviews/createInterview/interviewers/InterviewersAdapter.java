package com.erkprog.zensofthrcrm.ui.interviews.createInterview.interviewers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;

import java.util.ArrayList;

public class InterviewersAdapter extends ArrayAdapter<InterviewerItem> {

  private ArrayList<InterviewerItem> mInterviewers;
  Context mContext;

  public InterviewersAdapter(Context context, ArrayList<InterviewerItem> data){
    super(context, R.layout.interview_item, data);
    mInterviewers = data;
    mContext = context;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View itemView = convertView;

    if (itemView == null) {
      itemView = LayoutInflater.from(mContext).inflate(R.layout.interviewer_item, parent, false);
    }

    InterviewerItem interviewer = mInterviewers.get(position);

    TextView name = itemView.findViewById(R.id.intrvw_name);
    name.setText(String.format("%s %s", interviewer.getUser().getFirstName(), interviewer.getUser
        ().getLastName()));

    TextView email = itemView.findViewById(R.id.intrvw_email);
    email.setText(interviewer.getUser().getEmail());

    CheckBox checkBox = itemView.findViewById(R.id.intrvw_checkBox);
    checkBox.setChecked(interviewer.isChecked());

    return itemView;
  }
}
