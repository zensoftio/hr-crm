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
  private ItemClickListener<Candidate> mlistener;

  CandidatesAdapter(List<Candidate> candidates, ItemClickListener<Candidate> listener) {
    mCandidates = candidates;
    mlistener = listener;
  }

  @NonNull
  @Override
  public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_item, parent, false);
    return new CandidateViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CandidateViewHolder holder, int position) {
    final Candidate candidate = mCandidates.get(position);
    if (candidate != null) {

      holder.firstName.setText(candidate.getFirstName());
      holder.lastName.setText(candidate.getLastName());
      holder.status.setText(String.valueOf(candidate.getStatus()));

      if (candidate.getPosition() != null) {
        holder.position.setText(candidate.getPosition().getName());
      }

      holder.created.setText(String.format("created: %s", candidate.getCreated()));

      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          mlistener.onItemClick(candidate);
        }
      });
    }

  }

  @Override
  public int getItemCount() {
    return mCandidates.size();
  }

  static class CandidateViewHolder extends RecyclerView.ViewHolder {
    TextView firstName;
    TextView lastName;
    TextView position;
    TextView status;
    TextView created;

    CandidateViewHolder(View itemView) {
      super(itemView);

      firstName = itemView.findViewById(R.id.citem_firstName);
      lastName = itemView.findViewById(R.id.citem_lastName);
      status = itemView.findViewById(R.id.citem_status);
      position = itemView.findViewById(R.id.citem_position);
      created = itemView.findViewById(R.id.citem_created);
    }
  }
}
