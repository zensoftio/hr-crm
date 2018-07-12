package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;


import android.util.Log;

import com.erkprog.zensofthrcrm.data.db.SQLiteHelper;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.Department;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.entity.Position;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterviewsPresenter implements InterviewsContract.Presenter {

  private InterviewsContract.View mView;
  private RestServiceTest mService;
  private SQLiteHelper mSQLiteHelper;

  InterviewsPresenter(InterviewsContract.View view, RestServiceTest service, SQLiteHelper sqliteHelper) {
    mView = view;
    mService = service;
    mSQLiteHelper = sqliteHelper;
  }


  @Override
  public void getInterviewsInternet() {
    mService.getInterviews().enqueue(new Callback<InterviewsResponse>() {
      @Override
      public void onResponse(Call<InterviewsResponse> call, Response<InterviewsResponse> response) {
        if (isViewAttached()) {
          if (response.isSuccessful() && response.body() != null && response.body()
              .getInterviewList() != null) {
            mSQLiteHelper.saveInterviews(response.body().getInterviewList());
            mView.showInterviews(response.body().getInterviewList());

          } else {
            mView.showNoInterviews();
          }
        }
      }

      @Override
      public void onFailure(Call<InterviewsResponse> call, Throwable t) {
        if (isViewAttached())
          mView.showMessage(t.getMessage());
      }
    });

  }

  @Override
  public void getInterviewsLocal() {
    List<Interview> interviews = mSQLiteHelper.getInterviews();
    if (interviews != null){
      mView.showInterviews(interviews);
    }
    else {
      mView.showNoInterviews();
    }
  }

  @Override
  public void bind(InterviewsContract.View view) {
    mView = view;
  }

  @Override
  public void unbind() {
    mView = null;
  }

  private boolean isViewAttached() {
    return mView != null;
  }

}