package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import com.erkprog.zensofthrcrm.data.db.SQLiteHelper;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
  public void getInterviewsInternet(int interviewId) {

    mService.getDetailedInterview(interviewId).enqueue(new Callback<Interview>() {
      @Override
      public void onResponse(Call<Interview> call, Response<Interview> response) {
        if (isViewAttached()) {
          if (response.isSuccessful() && response.body() != null) {
            mSQLiteHelper.saveInterviews(new ArrayList<Interview>(Arrays.asList(response.body())));
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


  @Override
  public void getInterviewsLocal(int interviewId) {
    Interview interview = mSQLiteHelper.getInterview(String.valueOf(interviewId));
    if (interview != null) {
      mView.showInterviewDetails(interview);
    } else {
      mView.showNoInterviewDetails();
    }
  }
}
