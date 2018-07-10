package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidatesPresenter implements CandidatesContract.Presenter {

  private CandidatesContract.View mView;
  private RestServiceTest mApiService;

  public CandidatesPresenter(CandidatesContract.View view, RestServiceTest service) {
    mView = view;
    mApiService = service;
  }

  @Override
  public void loadCandidates() {
    mApiService.getCandidates().enqueue(new Callback<CandidatesResponse>() {
      @Override
      public void onResponse(Call<CandidatesResponse> call, Response<CandidatesResponse> response) {
        if (isViewAttached() && response.isSuccessful() && response.body() != null) {
          mView.showCandidates(response.body().getCandidateList());
        } else {
          mView.showMessage("Candidates list in response is null");
        }
      }

      @Override
      public void onFailure(Call<CandidatesResponse> call, Throwable t) {
        mView.showMessage(t.getMessage());
      }
    });
  }

  private boolean isViewAttached() {
    return mView != null;
  }

  @Override
  public void onCandidateItemClick(Candidate candidate) {
    mView.showCandidateDetailUi(candidate.getId());
  }

  @Override
  public void bind(CandidatesContract.View view) {
    mView = view;
  }

  @Override
  public void unbind() {
    mView = null;
  }
}
