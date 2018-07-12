package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import android.annotation.SuppressLint;
import android.util.Log;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.network.candidates.CandidatesRepository;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Response;

public class CandidatesPresenter implements CandidatesContract.Presenter, CandidatesRepository
    .OnCandidatesLoadFinishedListener {

  private static final String TAG = "mylog:CandidatesPresenter";

  private CandidatesContract.View mView;
  private CandidatesRepository mRepository;


  public CandidatesPresenter(CandidatesContract.View view, CandidatesRepository repository) {
    mView = view;
    mRepository = repository;
  }

  @Override
  public void loadCandidates() {
    mRepository.getCandidatesFromJson(this);
  }

  @Override
  public void openCandidateDetails(Candidate requestedCandidate) {

  }

  @Override
  public void onCandidateItemClick(Candidate candidate) {
    mView.showCandidateDetailUi(candidate.getId());
  }

  @SuppressLint("LongLogTag")
  @Override
  public void onFinished(Response<CandidatesResponse> response) {
    if (!mView.isActive()) {
      return;
    }
    if (response.isSuccessful()) {
      Log.d(" 2.0 getFeed > Full json res wrapped in pretty printed gson => ", new GsonBuilder().setPrettyPrinting().create().toJson(response));
      List<Candidate> candidates = response.body().getCandidateList();
      mView.showCandidates(candidates);


    } else {
      mView.showToast("Response is not successfull");
    }
  }

  @Override
  public void onFailure(Throwable t) {

  }
}
