package com.erkprog.zensofthrcrm.ui.vacancies.vacancyDetail;

import com.erkprog.zensofthrcrm.data.db.SQLiteHelper;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VacancyDetailPresenter implements VacancyDetailContract.Presenter {
  @Override
  public void getVacancyInternet(int vacancyId) {


  }

  @Override
  public void getVacancyLocal(int vacancyId) {

  }

  private VacancyDetailContract.View mView;
  private ApiInterface mService;
  private SQLiteHelper mSQLiteHelper;

  VacancyDetailPresenter(VacancyDetailContract.View view, ApiInterface service, SQLiteHelper
      sqLiteHelper) {
    mView = view;
    mService = service;
    mSQLiteHelper = sqLiteHelper;
  }


  @Override
  public void bind(VacancyDetailContract.View view) {
    mView = view;
  }

  private boolean isViewAttached() {
    return mView != null;
  }

  @Override
  public void unbind() {
    mView = null;
  }


}
