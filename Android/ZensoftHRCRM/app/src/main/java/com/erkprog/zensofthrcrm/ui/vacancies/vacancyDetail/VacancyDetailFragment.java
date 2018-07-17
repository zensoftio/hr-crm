package com.erkprog.zensofthrcrm.ui.vacancies.vacancyDetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.Evaluation;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.Interviewer;
import com.erkprog.zensofthrcrm.data.entity.Position;
import com.erkprog.zensofthrcrm.data.entity.SubTitle;
import com.erkprog.zensofthrcrm.data.entity.Title;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class VacancyDetailFragment extends Fragment implements VacancyDetailContract.View {

  private static final String EXTRA_VACANCY_ID = "vacancy_id";

  private VacancyDetailContract.Presenter mPresenter;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = new VacancyDetailPresenter(this, CRMApplication.getInstance(requireContext())
        .getApiService(), CRMApplication.getInstance(requireContext()).getSQLiteHelper());
    mPresenter.bind(this);
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_interview_detail, container, false);

    initUI(v);

    if (getArguments() != null)
      if (hasInternetConnection(v.getContext())) {
        mPresenter.getVacancyInternet(getArguments().getInt(EXTRA_VACANCY_ID));
      } else {
        mPresenter.getVacancyLocal(getArguments().getInt(EXTRA_VACANCY_ID));
      }

    return v;
  }

  private void initUI(View v) {


  }


  @Override
  public void onDestroy() {
    super.onDestroy();
    mPresenter.unbind();
  }


  @Override
  public void showMessage(String message) {

  }

  @Override
  public boolean hasInternetConnection(Context context) {
    return false;
  }

  @Override
  public void showProgress() {

  }

  @Override
  public void dismissProgress() {

  }

  @Override
  public void showVacancyDetails(Vacancy Vacancy) {

  }
}

