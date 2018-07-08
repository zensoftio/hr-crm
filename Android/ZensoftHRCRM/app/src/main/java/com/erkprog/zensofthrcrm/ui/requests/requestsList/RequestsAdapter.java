package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Request;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder> {
  private List<Request> mRequests;
  private Context mContext;
  private OnItemClickListener mListener;

  @NonNull
  @Override
  public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
    return new RequestViewHolder(v, mListener);
  }

  @Override
  public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
    Request request = mRequests.get(position);

    String name = request.getPosition().getName();
    holder.name.setText(name != null ? name : mContext.getString(R.string.not_defined));

    String deparment = request.getPosition().getDepartment().getName();
    holder.department.setText(deparment != null ? deparment : mContext.getString(R.string
        .not_defined));

    holder.status.setText(String.valueOf(request.getStatus()));
    holder.count.setText(String.valueOf(request.getCount()));

    String created = request.getCreated();

    holder.created.setText(created != null ? created : mContext.getString(R.string.not_defined));
  }

  @Override
  public int getItemCount() {
    return mRequests != null ? mRequests.size() : 0;
  }

  public void loadNewData(List<Request> data){
    mRequests = data;
    notifyDataSetChanged();
  }

  public Request getRequest(int position){
    return mRequests.get(position);
  }

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  public interface OnItemClickListener {
    void onItemClick(int position);
  }

  static class RequestViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView department;
    TextView status;
    TextView count;
    TextView created;

    public RequestViewHolder(View itemView, final OnItemClickListener listener) {
      super(itemView);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          int position = getAdapterPosition();
          if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position);
          }
        }
      });

      name = itemView.findViewById(R.id.ritem_name);
      department = itemView.findViewById(R.id.ritem_department);
      status = itemView.findViewById(R.id.ritem_status);
      count = itemView.findViewById(R.id.ritem_count);
      created = itemView.findViewById(R.id.ritem_created);
    }
  }
}
