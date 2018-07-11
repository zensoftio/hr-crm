package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.content.Context;
import android.util.Log;

import com.erkprog.zensofthrcrm.data.entity.Interview;


public class InterviewDetailPresenter implements InterviewDetailContract.Presenter,
    InterviewDetailContract.Repository.OnFinishedListener {

  private InterviewDetailContract.View mView;
  private InterviewDetailContract.Repository mRepository;

  public InterviewDetailPresenter(InterviewDetailContract.View view, InterviewDetailContract
      .Repository
      repository) {
    this.mView = view;
    this.mRepository = repository;
  }

  @Override
  public void getDetailedInterview(Context mContext, Integer interviewId) {
    mRepository.getInterviewDetails(this, mContext);
  }

  @Override
  public void onDestroy() {

  }

  @Override
  public void onFinished(Interview interview) {
    mView.showInterviewDetails(interview);
  }

  @Override
  public void onFailure(Throwable t) {
    // something on failure
    Log.d("me", "onFailure: " + t.getMessage());
  }
}
