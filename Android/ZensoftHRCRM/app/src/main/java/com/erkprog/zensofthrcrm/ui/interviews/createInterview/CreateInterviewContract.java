package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

import com.erkprog.zensofthrcrm.ui.BaseView;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

import java.util.List;

public interface CreateInterviewContract {

  interface View extends BaseView {

    void startDatePicker();
  }

  interface Presenter extends ILifecycle<View> {

    void onSetDateButtonClick();

    void onCreateButtonClick(int candidateId, List<Integer> interviewersId, String date);

  }
}
