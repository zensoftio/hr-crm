package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

public class CandidateDetailPresenter implements CandidateDetailContract.Presenter{

  private CandidateDetailContract.View mView;

  public CandidateDetailPresenter(CandidateDetailContract.View view) {
    mView = view;
  }

  @Override
  public void loadCandidateInfo() {

  }
}
