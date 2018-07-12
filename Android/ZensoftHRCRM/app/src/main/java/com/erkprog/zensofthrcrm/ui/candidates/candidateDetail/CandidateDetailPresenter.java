package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.data.network.remote.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidateDetailPresenter implements CandidateDetailContract.Presenter {
  private static final String TAG = "CandidateDetailPresente";
  private CandidateDetailContract.View mView;
  private Candidate mCandidate;
  //  private RestServiceTest mApiService;
  private ApiInterface mApiService;

  CandidateDetailPresenter(ApiInterface service) {
    mApiService = service;
  }

  @Override
  public void loadCandidateInfo(int candidateId) {
    mApiService.getDetailedCandidate(candidateId).enqueue(new Callback<Candidate>() {
      @Override
      public void onResponse(Call<Candidate> call, Response<Candidate> response) {
        if (isViewAttached()) {
          if (response.isSuccessful() && response.body() != null) {
            mCandidate = response.body();
            mView.showCandidateDetails(mCandidate);
          } else {
            mView.showMessage("Candidate response is null");
          }
        }
      }

      @Override
      public void onFailure(Call<Candidate> call, Throwable t) {
        if (isViewAttached()) {
          mView.showMessage(t.getMessage());
        }
      }
    });
  }

  private boolean isViewAttached() {
    return mView != null;
  }

  @Override
  public void onInterviewItemClicked(CandidateInterviewItem interviewItem) {
    //TODO: mView.showDetailedInterview() here, showMessage just for test
    mView.showMessage(interviewItem.getDate());
  }

  @Override
  public void onCvItemClicked(Cv cvItem) {
    //TODO: download cv file here, showMessage just for test
    mView.showMessage(cvItem.getLink());
  }

  @Override
  public void onCreateInterviewClicked() {
    if (!isViewAttached() || (mCandidate == null)) {
      return;
    }

    mView.startCreateInterview(mCandidate);
  }

  @Override
  public void bind(CandidateDetailContract.View view) {
    mView = view;
  }

  @Override
  public void unbind() {
    mView = null;
  }
}
