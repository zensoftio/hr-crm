package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.entity.Interview;

import java.util.List;

public interface InterviewsContract {

  interface View {

    void showInterviews(List<Interview> interviews);

    void showToast();

    void showToast(Throwable t);

    void showNoInterviews();

    void showProgress();

    void hideProgress();


  }

  interface Presenter {

    void onDestroy();

    // ?? void onRefreshData();

    void getInterviews(Context mContext);

  }

  interface Repository {

    interface OnFinishedListener {
      void onFinished(List<Interview> interviews);

      void onFailure(Throwable t);
    }

    void getInterviewsList(OnFinishedListener onFinishedListener, Context mContext);
  }

}
