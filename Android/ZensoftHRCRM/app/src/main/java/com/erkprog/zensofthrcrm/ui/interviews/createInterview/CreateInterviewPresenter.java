package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

import java.util.Date;

public class CreateInterviewPresenter implements CreateInterviewContract.Presenter {

  private CreateInterviewContract.View mView;

  public CreateInterviewPresenter(CreateInterviewContract.View view) {
    mView = view;
  }

  @Override
  public void onSetDateButtonClick() {
    mView.startDatePicker();
  }

  @Override
  public void onCreateButtonClick() {

  }

}
