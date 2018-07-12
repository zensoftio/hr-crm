package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import com.erkprog.zensofthrcrm.data.entity.VacanciesResponse;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;
import com.erkprog.zensofthrcrm.data.network.remote.ApiInterface;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VacanciesPresenter implements VacanciesContract.Presenter {

  private VacanciesContract.View mView;
  private ApiInterface mApiService;

  VacanciesPresenter(ApiInterface service) {
    mApiService = service;
  }

  @Override
  public void loadData() {
    mView.showProgress();
    mApiService.getVacancies().enqueue(new Callback<VacanciesResponse>() {
      @Override
      public void onResponse(Call<VacanciesResponse> call, Response<VacanciesResponse> response) {
        if (isViewAttached()) {
          mView.dismissProgress();
          if (response.isSuccessful() && response.body().getVacancyList() != null) {
            mView.showVacancies(response.body().getVacancyList());
          } else {
            mView.showMessage("Vacancies list in response is null");
          }
        }
      }

      @Override
      public void onFailure(Call<VacanciesResponse> call, Throwable t) {
        if (isViewAttached()) {
          mView.dismissProgress();
          mView.showMessage(t.getMessage());
        }
      }
    });
  }

  @Override
  public void onVacancyItemClick(Vacancy vacancy) {
    // TODO: implement mView.startEditVacancy() here
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
