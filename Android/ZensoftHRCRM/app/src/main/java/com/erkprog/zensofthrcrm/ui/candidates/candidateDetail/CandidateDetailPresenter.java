package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.content.Context;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidateDetailPresenter implements CandidateDetailContract.Presenter {
  private CandidateDetailContract.View mView;
  private Context mContext;
  private Candidate mCandidate;
  private ApiInterface mApiService;

  CandidateDetailPresenter(Context context, ApiInterface service) {
    mApiService = service;
    mContext = context;
  }

  @Override
  public void loadCandidateInfo(int candidateId) {
    mView.showProgress();
    mApiService.getDetailedCandidate(candidateId).enqueue(new Callback<Candidate>() {
      @Override
      public void onResponse(Call<Candidate> call, Response<Candidate> response) {
        if (isViewAttached()) {
          mView.dismissProgress();
          if (response.isSuccessful() && response.body() != null) {
            mCandidate = response.body();
            mView.showCandidateDetails(mCandidate);
          } else {
            mView.showMessage(mContext.getString(R.string.cd_response_null));
          }
        }
      }

      @Override
      public void onFailure(Call<Candidate> call, Throwable t) {
        if (isViewAttached()) {
          mView.dismissProgress();
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
