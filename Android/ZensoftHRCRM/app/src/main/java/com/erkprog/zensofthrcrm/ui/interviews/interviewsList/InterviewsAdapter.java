
package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.Department;
import com.erkprog.zensofthrcrm.data.entity.Interview;

import java.util.List;

public class InterviewsAdapter extends RecyclerView.Adapter<InterviewsAdapter.InterviewViewHolder> {

  private List<Interview> mInterviews;
  private Context mContext;
  private RecyclerItemClickListener recyclerItemClickListener;


  public InterviewsAdapter(Context mContext, List<Interview> mInterviews, RecyclerItemClickListener recyclerItemClickListener) {
    this.mInterviews = mInterviews;
    this.mContext = mContext;
    this.recyclerItemClickListener = recyclerItemClickListener;
  }

  @NonNull
  @Override
  public InterviewsAdapter.InterviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interview_item, parent, false);
    return new InterviewViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull InterviewViewHolder holder, final int position) {

    final Interview interview = mInterviews.get(position);

    if (interview.getCandidate() != null) {
      if (interview.getCandidate().getPosition().getDepartment() != null) {
        Department department = interview.getCandidate().getPosition().getDepartment();
        Candidate candidate = interview.getCandidate();
        holder.firstName.setText(candidate.getFirstName() != null ? candidate.getFirstName() : "No First Name");
        holder.lastName.setText(candidate.getLastName() != null ? candidate.getLastName() : "No Last Name");
        holder.department.setText(department.getName() != null ? department.getName() : "No Department");
      }
    }

    holder.date.setText(interview.getDate() != null ? interview.getDate() : "No Date");
    holder.status.setText(interview.getStatus() != null ? interview.getStatus().toString() : "No Status");


    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        recyclerItemClickListener.onItemClick(position);
      }
    });

  }

  @Override
  public int getItemCount() {
    return (mInterviews != null ? mInterviews.size() : 0);
  }

  static class InterviewViewHolder extends RecyclerView.ViewHolder {

    TextView firstName;
    TextView lastName;
    TextView department;
    TextView date;
    TextView status;

    public InterviewViewHolder(View itemView) {
      super(itemView);
      firstName = itemView.findViewById(R.id.iitem_firstName);
      lastName = itemView.findViewById(R.id.iitem_lastName);
      department = itemView.findViewById(R.id.iitem_department);
      date = itemView.findViewById(R.id.iitem_date);
      status = itemView.findViewById(R.id.iitem_status);
    }
  }
}