package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

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

  @Override
  public void bind(CreateInterviewContract.View view) {
    this.mView = view;
  }

  @Override
  public void unbind() {
    this.mView = null;
  }
}
