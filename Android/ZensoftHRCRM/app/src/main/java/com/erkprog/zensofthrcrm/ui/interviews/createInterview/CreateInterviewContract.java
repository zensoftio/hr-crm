package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

import com.erkprog.zensofthrcrm.ui.ILifecycle;

public class CreateInterviewContract {

  interface View {

    void showMessage(String message);

    void startDatePicker();
  }

  interface Presenter extends ILifecycle<View> {

    void onSetDateButtonClick();

    void onCreateButtonClick();

  }
}
