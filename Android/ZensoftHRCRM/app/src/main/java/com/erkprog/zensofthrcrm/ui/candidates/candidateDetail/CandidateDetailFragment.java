package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;

public class CandidateDetailFragment extends Fragment implements CandidateDetailContract.View{

  private CandidateDetailContract.Presenter mPresenter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_candidate_detail, container, false);
    mPresenter = new CandidateDetailPresenter(this);

    return v;

  }

  @Override
  public void showCandidateDetails(Candidate candidate) {

  }

  @Override
  public void showLoadingCandidateError() {

  }

  @Override
  public void showToast(String message) {

  }
}
