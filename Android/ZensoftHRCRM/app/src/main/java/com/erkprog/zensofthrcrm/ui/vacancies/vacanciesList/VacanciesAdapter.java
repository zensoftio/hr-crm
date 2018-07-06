package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;

import java.util.List;

public class VacanciesAdapter extends RecyclerView.Adapter<VacanciesAdapter.VacancyViewHolder> {

  private List<Vacancy> mVacancies;
  private Context mContext;
  private OnItemClickListener mListener;

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  public VacanciesAdapter(Context context, List<Vacancy> vacancies) {
    mVacancies = vacancies;
    mContext = context;
  }

  public void loadNewData(List<Vacancy> data) {
    mVacancies = data;
    this.notifyDataSetChanged();
  }

  @NonNull
  @Override
  public VacancyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacancy_item, parent,
        false);
    return new VacancyViewHolder(view, mListener);
  }

  @Override
  public void onBindViewHolder(@NonNull VacancyViewHolder holder, int position) {
    Vacancy vacancy = mVacancies.get(position);

    holder.name.setText(vacancy.getName() != null ? vacancy.getName() : "");
    holder.status.setText(String.valueOf(vacancy.getStatus()));
    holder.created.setText(vacancy.getCreated() != null ? vacancy.getCreated() : "");
    holder.lastPublish.setText(vacancy.getLast_published() != null ? vacancy.getLast_published()
        : "");
  }

  @Override
  public int getItemCount() {
    return (mVacancies != null ? mVacancies.size() : 0);
  }

  public Vacancy getVacancy(int position) {
    return mVacancies.get(position);
  }

  public interface OnItemClickListener {
    void onItemClick(int position);
  }

  static class VacancyViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView status;
    TextView created;
    TextView lastPublish;

    VacancyViewHolder(View itemView, final OnItemClickListener listener) {
      super(itemView);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (listener != null) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
              listener.onItemClick(position);
            }
          }
        }
      });

      name = itemView.findViewById(R.id.vitem_name);
      status = itemView.findViewById(R.id.vitem_status);
      created = itemView.findViewById(R.id.vitem_created);
      lastPublish = itemView.findViewById(R.id.vitem_last_published);
    }
  }
}
