package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import com.erkprog.zensofthrcrm.data.entity.VacanciesResponse;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VacanciesPresenter implements VacanciesContract.Presenter {

  private static final String TAG = "VACANCIES PRESENTER";

  private VacanciesContract.View mView;
  private RestServiceTest mApiService;

  VacanciesPresenter(VacanciesContract.View view, RestServiceTest service) {
    mView = view;
    mApiService = service;
  }

  @Override
  public void loadData() {
    mApiService.getVacancies().enqueue(new Callback<VacanciesResponse>() {
      @Override
      public void onResponse(Call<VacanciesResponse> call, Response<VacanciesResponse> response) {
        if (isViewAttached() && response.isSuccessful() && response.body() != null) {
          mView.showVacancies(response.body().getVacancyList());
        } else {
          mView.showMessage("Vacancies list in response is null");
        }
      }

      @Override
      public void onFailure(Call<VacanciesResponse> call, Throwable t) {
        mView.showMessage(t.getMessage());
      }
    });
  }

  @Override
  public void onVacancyItemClick(Vacancy vacancy) {

  }

  private boolean isViewAttached() {
    return mView != null;
  }

  @Override
  public void bind(VacanciesContract.View view) {
    this.mView = view;
  }

  @Override
  public void unbind() {
    this.mView = null;
  }
}
