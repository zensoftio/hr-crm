package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InterviewDetailPresenter implements InterviewDetailContract.Presenter {

  private InterviewDetailContract.View mView;
  private RestServiceTest mService;

  InterviewDetailPresenter(InterviewDetailContract.View view, RestServiceTest service) {
    mView = view;
    mService = service;
  }

  @Override
  public void getDetailedInterview() {

    mService.getDetailedInterview().enqueue(new Callback<Interview>() {
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
  public void onDestroy() {
    unbind();
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
