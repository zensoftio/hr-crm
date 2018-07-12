package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.ui.BaseView;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

public interface InterviewDetailContract {

  public interface View extends BaseView {

    void showInterviewDetails(Interview interview);

    void showNoInterviewDetails();

    void showMessage(String t);

    void showLoadingInterviewError();

  }

  public interface Presenter extends ILifecycle<View> {

    void getDetailedInterview();

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