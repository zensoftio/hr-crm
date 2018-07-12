package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.ui.ItemClickListener;
import com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.CandidateDetail;

import java.util.ArrayList;
import java.util.List;

public class CandidatesFragment extends Fragment implements CandidatesContract.View, ItemClickListener {

  private CandidatesContract.Presenter mPresenter;
  private CandidatesAdapter mAdapter;
  private RecyclerView mRecyclerView;
  private ProgressBar mProgressBar;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initPresenter();
  }

  private void initPresenter() {
    mPresenter = new CandidatesPresenter(requireContext(),
        CRMApplication.getInstance(requireContext()).getApiService());
    mPresenter.bind(this);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_candidates_list, container, false);
    initRecyclerView(v);
    mProgressBar = v.findViewById(R.id.candidates_progress_bar);
    dismissProgress();
    return v;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPresenter.loadCandidates();
  }

  private void initRecyclerView(View v) {
    mRecyclerView = v.findViewById(R.id.recycler_view_all_candidates);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(layoutManager);
  }

  @Override
  public void showCandidates(List<Candidate> candidates) {
    mAdapter = new CandidatesAdapter(candidates, this);
    mRecyclerView.setAdapter(mAdapter);
  }

  @Override
  public void showCandidateDetailUi(int candidateId) {
    Intent intent = CandidateDetail.getIntent(getActivity(), candidateId);
    startActivity(intent);
  }

  @Override
  public void showMessage(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void showProgress() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void dismissProgress() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override
  public void onItemClick(int position) {
    mPresenter.onCandidateItemClick(mAdapter.getCandidate(position));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mPresenter.unbind();
  }
}
