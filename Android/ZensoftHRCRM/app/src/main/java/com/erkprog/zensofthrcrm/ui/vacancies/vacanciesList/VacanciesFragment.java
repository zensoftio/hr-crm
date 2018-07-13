package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;
import com.erkprog.zensofthrcrm.ui.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class VacanciesFragment extends Fragment implements VacanciesContract.View,
    ItemClickListener<Vacancy> {

  private static final String TAG = "VACANCIES FRAGMENT";

  private VacanciesContract.Presenter mPresenter;
  private VacanciesAdapter mAdapter;
  private RecyclerView mRecyclerView;
  private ProgressBar mProgressBar;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initPresenter();
  }

  private void initPresenter() {
    mPresenter = new VacanciesPresenter(requireContext(),
        CRMApplication.getInstance(requireContext()).getApiService());
    mPresenter.bind(this);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_vacancies_list, container, false);
    mProgressBar = v.findViewById(R.id.vacancies_progress_bar);
    dismissProgress();
    initRecyclerView(v);
    return v;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPresenter.loadData();
  }

  private void initRecyclerView(View v) {
    mRecyclerView = v.findViewById(R.id.recycler_view_all_vacancies);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(layoutManager);
  }

  @Override
  public void showVacancies(List<Vacancy> vacancies) {
    mAdapter = new VacanciesAdapter(vacancies, this);
    mRecyclerView.setAdapter(mAdapter);
  }

  @Override
  public void showMessage(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public boolean hasInternetConnection(Context context) {
    return false;
  }

  @Override
  public void showProgress() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void dismissProgress() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override
  public void onItemClick(Vacancy item) {
    mPresenter.onVacancyItemClick(item);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mPresenter.unbind();
  }

  public static VacanciesFragment newInstance() {
    return new VacanciesFragment();
  }
}
