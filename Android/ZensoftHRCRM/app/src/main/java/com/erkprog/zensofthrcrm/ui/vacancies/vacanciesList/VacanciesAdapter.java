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
  private ItemClickListener<Vacancy> mListener;


  VacanciesAdapter(List<Vacancy> vacancies, ItemClickListener<Vacancy> listener) {
    mVacancies = vacancies;
    mListener = listener;
  }

  @NonNull
  @Override
  public VacancyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacancy_item, parent,
        false);
    return new VacancyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull VacancyViewHolder holder, int position) {
    final Vacancy vacancy = mVacancies.get(position);

    if (vacancy != null) {
      holder.name.setText(vacancy.getName());
      holder.created.setText(String.format("created: %s", vacancy.getDateCreated()));
      holder.lastPublish.setText(String.format("last published: %s", vacancy.getDateLastPublished()));

      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mListener.onItemClick(vacancy);
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return mVacancies.size();
  }

  static class VacancyViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView created;
    TextView lastPublish;

    VacancyViewHolder(View itemView) {
      super(itemView);

      name = itemView.findViewById(R.id.vitem_name);
      created = itemView.findViewById(R.id.vitem_created);
      lastPublish = itemView.findViewById(R.id.vitem_last_published);
    }
  }
}
