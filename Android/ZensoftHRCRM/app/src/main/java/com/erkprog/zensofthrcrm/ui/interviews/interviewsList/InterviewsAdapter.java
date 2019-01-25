
package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;

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
import com.erkprog.zensofthrcrm.ui.ItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InterviewsAdapter extends RecyclerView.Adapter<InterviewsAdapter.InterviewViewHolder> {

  private List<Interview> mInterviews;
  private ItemClickListener<Interview> mRecyclerItemClickListener;


  InterviewsAdapter(List<Interview> interviews, ItemClickListener<Interview>
      recyclerItemClickListener) {
    mInterviews = interviews;
    mRecyclerItemClickListener = recyclerItemClickListener;
  }

  @NonNull
  @Override
  public InterviewsAdapter.InterviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interview_item, parent, false);
    return new InterviewViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final InterviewViewHolder holder, int position) {

    final Interview interview = mInterviews.get(holder.getAdapterPosition());

    if (interview.getCandidate() != null) {
      Candidate candidate = interview.getCandidate();
      holder.firstName.setText(candidate.getFirstName());
      holder.lastName.setText(candidate.getLastName());
      if (candidate.getPosition() != null && candidate.getPosition().getDepartment() != null) {
        Department department = candidate.getPosition().getDepartment();
        holder.department.setText(department.getName());
      }
    }

    holder.date.setText(interview.getDate());
    holder.status.setText(String.valueOf(interview.getStatus()));

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mRecyclerItemClickListener.onItemClick(interview);
      }
    });

  }

  @Override
  public int getItemCount() {
    return (mInterviews != null ? mInterviews.size() : 0);
  }

  static class InterviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iitem_firstName)
    TextView firstName;

    @BindView(R.id.iitem_lastName)
    TextView lastName;

    @BindView(R.id.iitem_department)
    TextView department;

    @BindView(R.id.iitem_date)
    TextView date;

    @BindView(R.id.iitem_status)
    TextView status;

    public InterviewViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}