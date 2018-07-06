package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.User;

import java.util.List;

class InterviewsAdapter extends BaseAdapter {
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private List<CandidateInterviewItem> mInterviews;

  InterviewsAdapter(Context context, List<CandidateInterviewItem> data) {
    mContext = context;
    mInterviews = data;
    mLayoutInflater = LayoutInflater.from(mContext);
  }

  public void setData(List<CandidateInterviewItem> newData) {
    mInterviews = newData;
    this.notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return mInterviews.size();
  }

  @Override
  public Object getItem(int position) {
    return mInterviews.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View v = mLayoutInflater.inflate(R.layout.cd_interview_item, parent, false);
    TextView status = v.findViewById(R.id.cd_interview_status);
    TextView date = v.findViewById(R.id.cd_interview_date);
    TextView interviewersText = v.findViewById(R.id.cd_interview_interviewers);

    CandidateInterviewItem interview = (CandidateInterviewItem) getItem(position);
    status.setText(interview.getStatus().toString());
    date.setText(interview.getDate());
    List<User> interviewers = interview.getInterviewers();
    StringBuilder users = new StringBuilder();
    users.append("interviewers:\n");
    for (User interviewer : interviewers) {
      String firstName =
          interviewer.getFirstName() != null ? interviewer.getFirstName() : "";
      String lastName =
          interviewer.getLastName() != null ? interviewer.getLastName() : "";
      users.append("-").append(firstName).append(" ").append(lastName).append("\n");
    }
    interviewersText.setText(users);

    return v;
  }
}