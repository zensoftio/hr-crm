package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.ui.ItemClickListener;

import java.util.List;

public class CandidatesAdapter extends RecyclerView.Adapter<CandidatesAdapter.CandidateViewHolder> {

  private List<Candidate> mCandidates;
  private ItemClickListener mlistener;


  CandidatesAdapter(List<Candidate> candidates, ItemClickListener listener) {
    mCandidates = candidates;
    mlistener = listener;
  }

  @NonNull
  @Override
  public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_item, parent, false);
    return new CandidateViewHolder(view, mlistener);
  }

  @Override
  public void onBindViewHolder(@NonNull CandidateViewHolder holder, int position) {
    Candidate candidate = mCandidates.get(position);
    if (candidate != null) {

      holder.firstName.setText(candidate.getFirstName());
      holder.lastName.setText(candidate.getLastName());
      holder.status.setText(String.valueOf(candidate.getStatus()));
    }

  }

  @Override
  public int getItemCount() {
    return (mCandidates != null ? mCandidates.size() : 0);
  }

  public void loadNewData(List<Candidate> candidates) {
    mCandidates = candidates;
    this.notifyDataSetChanged();
  }

  public Candidate getCandidate(int position) {
    return mCandidates.get(position);
  }

  static class CandidateViewHolder extends RecyclerView.ViewHolder {
    TextView firstName;
    TextView lastName;
    TextView requestName;
    TextView status;

    CandidateViewHolder(View itemView, final ItemClickListener listener) {
      super(itemView);

      firstName = itemView.findViewById(R.id.citem_firstName);
      lastName = itemView.findViewById(R.id.citem_lastName);
      requestName = itemView.findViewById(R.id.citem_request_name);
      status = itemView.findViewById(R.id.citem_status);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          listener.onItemClick(getAdapterPosition());
        }
      });
    }

  }
}
