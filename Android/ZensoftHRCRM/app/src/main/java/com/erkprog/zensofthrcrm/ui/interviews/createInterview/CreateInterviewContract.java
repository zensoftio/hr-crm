package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

public class CreateInterviewContract {

  interface View {
    
    void showToast(String message);

    void startDatePicker();
  }

  interface Presenter {
    
    void onSetDateButtonClick();
    
    void onCreateButtonClick();

  }
}
