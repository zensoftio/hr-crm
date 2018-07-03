package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import com.erkprog.zensofthrcrm.data.entity.Interview;

public interface InterviewDetailContract {

  public interface View {

    void showInterviewDetails(Interview interview);

    void showLoadingInterviewError();

    void showToast(String message);

  }

  public interface Presenter {

    void loadInterviewInfo();

  }
}