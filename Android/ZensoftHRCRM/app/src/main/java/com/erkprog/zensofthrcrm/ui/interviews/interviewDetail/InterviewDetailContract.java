package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.entity.Interview;

public interface InterviewDetailContract {

  public interface View {

    void showInterviewDetails(Interview interview);

    void showLoadingInterviewError();

    void showToast(String message);

  }

  public interface Presenter {

    void onDestroy();

    void getDetailedInterview(Context context, Integer interviewId);

  }

  interface Repository {

    interface OnFinishedListener {
      void onFinished(Interview interview);

      void onFailure(Throwable t);
    }

    void getInterviewDetails(InterviewDetailContract.Repository.OnFinishedListener
                                 onFinishedListener,
                             Context mContext);
  }
}