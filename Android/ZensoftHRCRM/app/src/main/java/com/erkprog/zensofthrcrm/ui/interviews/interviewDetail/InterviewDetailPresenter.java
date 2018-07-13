package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import com.erkprog.zensofthrcrm.data.db.SQLiteHelper;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InterviewDetailPresenter implements InterviewDetailContract.Presenter {

  private InterviewDetailContract.View mView;
  private ApiInterface mService;
  private SQLiteHelper mSQLiteHelper;

  InterviewDetailPresenter(InterviewDetailContract.View view, ApiInterface service, SQLiteHelper
      sqLiteHelper) {
    mView = view;
    mService = service;
    mSQLiteHelper = sqLiteHelper;
  }

  @Override
  public void getDetailedInterview(int interviewId) {

    mService.getDetailedInterview(interviewId).enqueue(new Callback<Interview>() {
      @Override
      public void onResponse(Call<Interview> call, Response<Interview> response) {
        if (isViewAttached()) {
          if (response.isSuccessful() && response.body() != null) {
            mView.showInterviewDetails(response.body());
          } else {
            mView.showNoInterviewDetails();
          }
        }
      }

      @Override
      public void onFailure(Call<Interview> call, Throwable t) {
        if (isViewAttached()) {
          mView.showMessage(t.getMessage());
        }
      }
    });


  }

  @Override
  public void bind(InterviewDetailContract.View view) {
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
