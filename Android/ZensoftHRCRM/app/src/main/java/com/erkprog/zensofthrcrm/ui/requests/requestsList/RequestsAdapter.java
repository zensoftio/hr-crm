package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Request;
import com.erkprog.zensofthrcrm.ui.ItemClickListener;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder> {
  private List<Request> mRequests;
  private ItemClickListener mListener;

  RequestsAdapter(List<Request> requests, ItemClickListener listener) {
    mRequests = requests;
    mListener = listener;
  }

  @NonNull
  @Override
  public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
    return new RequestViewHolder(v, mListener);
  }

  @Override
  public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
    Request request = mRequests.get(position);
    if (request != null) {

      if (request.getPosition() != null) {
        holder.name.setText(request.getPosition().getName());
      }

      if (request.getPosition().getDepartment() != null) {
        holder.department.setText(request.getPosition().getDepartment().getName());
      }

      holder.status.setText(String.valueOf(request.getStatus()));

      holder.count.setText(String.format("count: %s", String.valueOf(request.getCount())));

      holder.created.setText(String.format("created: %s", request.getCreated()));
    }
  }

  @Override
  public int getItemCount() {
    return mRequests.size();
  }

  public void loadNewData(List<Request> data) {
    mRequests = data;
    notifyDataSetChanged();
  }

  public Request getRequest(int position) {
    return mRequests.get(position);
  }

  static class RequestViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView department;
    TextView status;
    TextView count;
    TextView created;

    RequestViewHolder(View itemView, final ItemClickListener listener) {
      super(itemView);

      name = itemView.findViewById(R.id.ritem_name);
      department = itemView.findViewById(R.id.ritem_department);
      status = itemView.findViewById(R.id.ritem_status);
      count = itemView.findViewById(R.id.ritem_count);
      created = itemView.findViewById(R.id.ritem_created);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          listener.onItemClick(getAdapterPosition());
        }
      });
    }
  }
}
