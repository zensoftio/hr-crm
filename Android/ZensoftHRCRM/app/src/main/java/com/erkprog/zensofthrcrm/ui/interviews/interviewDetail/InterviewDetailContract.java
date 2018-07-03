package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import com.erkprog.zensofthrcrm.data.entity.Interview;

public interface InterviewDetailContract {

  public interface View {

    void showCandidateDetails(Interview interview);

    void showLoadingCandidateError();

    void showToast(String message);

  }

  public interface Presenter {

    void loadCandidateInfo();

  }
}