package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;
import com.erkprog.zensofthrcrm.ui.ItemClickListener;

import java.util.List;

public class VacanciesAdapter extends RecyclerView.Adapter<VacanciesAdapter.VacancyViewHolder> {

  private List<Vacancy> mVacancies;
  private ItemClickListener mListener;


  public VacanciesAdapter(List<Vacancy> vacancies, ItemClickListener listener) {
    mVacancies = vacancies;
    mListener = listener;
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

    if (vacancy != null) {
      holder.name.setText(vacancy.getName());
      holder.status.setText(String.valueOf(vacancy.getStatus()));
      holder.created.setText(vacancy.getCreated());
      holder.lastPublish.setText(vacancy.getLast_published());
    }
  }

  @Override
  public int getItemCount() {
    return (mVacancies != null ? mVacancies.size() : 0);
  }

  public Vacancy getVacancy(int position) {
    return mVacancies.get(position);
  }

  static class VacancyViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView status;
    TextView created;
    TextView lastPublish;

    VacancyViewHolder(View itemView, final ItemClickListener listener) {
      super(itemView);

      name = itemView.findViewById(R.id.vitem_name);
      status = itemView.findViewById(R.id.vitem_status);
      created = itemView.findViewById(R.id.vitem_created);
      lastPublish = itemView.findViewById(R.id.vitem_last_published);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          listener.onItemClick(getAdapterPosition());
        }
      });
    }
  }
}
