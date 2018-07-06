package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;
import com.erkprog.zensofthrcrm.data.network.candidates.CandidatesRepository;

import java.util.ArrayList;
import java.util.List;

public class VacanciesFragment extends Fragment implements VacanciesContract.View,
    VacanciesAdapter.OnItemClickListener {

  private static final String TAG = "VACANCIES FRAGMENT";

  private VacanciesContract.Presenter mPresenter;
  private VacanciesAdapter mAdapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_vacancies_list, container, false);
    initRecyclerView(v);
    mPresenter = new VacanciesPresenter(this, new CandidatesRepository(getActivity()));
    mPresenter.loadData();
    return v;
  }

  private void initRecyclerView(View v) {

    final RecyclerView recyclerView = v.findViewById(R.id.recycler_view_all_vacancies);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);

    List<Vacancy> vacancies = new ArrayList<>();
    mAdapter = new VacanciesAdapter(getActivity(), vacancies);
    mAdapter.setOnItemClickListener(this);
    recyclerView.setAdapter(mAdapter);
  }

  public static VacanciesFragment newInstance() {
    VacanciesFragment fragment = new VacanciesFragment();
    return fragment;
  }

  @Override
  public void showVacancies(List<Vacancy> vacancies) {
    Log.d(TAG, "showVacancies: start");
    mAdapter.loadNewData(vacancies);
    Log.d(TAG, "showVacancies: " + mAdapter.getItemCount());
  }

  @Override
  public void showVacancyDetail(int vacancyId) {

  }

  @Override
  public void showToast(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public boolean isActive() {
    return isAdded();
  }

  @Override
  public void onItemClick(int position) {
    mPresenter.onVacancyItemClick(mAdapter.getVacancy(position));
  }
}
