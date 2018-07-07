package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import android.util.Log;

import com.erkprog.zensofthrcrm.data.DataRepository;
import com.erkprog.zensofthrcrm.data.entity.VacanciesResponse;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;
import com.erkprog.zensofthrcrm.data.network.RemoteDataSource;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Response;

public class VacanciesPresenter implements VacanciesContract.Presenter, RemoteDataSource.OnVacanciesLoadFinishedListener {

  private static final String TAG = "VACANCIES PRESENTER";

  private VacanciesContract.View mView;
  private DataRepository mRepository;

  VacanciesPresenter(VacanciesContract.View view, DataRepository repository) {
    mView = view;
    mRepository = repository;
  }

  @Override
  public void loadData() {
    mRepository.getVacanciesFromJson(this);
  }

  @Override
  public void onVacancyItemClick(Vacancy vacancy) {

  }

  @Override
  public void onFinished(Response<VacanciesResponse> response) {
    if (!mView.isActive()) {
      return;
    }

    if (response.isSuccessful()) {

      Log.d(TAG, new GsonBuilder().setPrettyPrinting().create().toJson(response));
      List<Vacancy> vacancies = response.body().getVacancyList();

      if (vacancies != null) {
        mView.showVacancies(vacancies);
      } else {
        Log.d(TAG, "onFinished: " + "vacancies list in response is null");
      }

    } else {
      mView.showToast("Response is not successfull");
    }
  }

  @Override
  public void onFailure(Throwable t) {

  }
}
