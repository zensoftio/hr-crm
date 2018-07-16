package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.ui.ItemClickListener;
import com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.CandidateDetail;
import com.erkprog.zensofthrcrm.ui.interviews.interviewDetail.InterviewDetail;

import java.util.List;

public class InterviewsFragment extends Fragment implements InterviewsContract.View,
    ItemClickListener<Interview> {

  private InterviewsContract.Presenter mPresenter;
  private RecyclerView mRecyclerView;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = new InterviewsPresenter(this, CRMApplication.getInstance(requireContext())
        .getApiService(), CRMApplication.getInstance(requireContext()).getSQLiteHelper());
    mPresenter.bind(this);
  }


  @Override
  public void onDestroy() {
    super.onDestroy();
    mPresenter.unbind();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_interviews_list, container, false);

    mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_all_interviews);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(v.getContext());
    mRecyclerView.setLayoutManager(layoutManager);

    if (hasInternetConnection(v.getContext())) {
      mPresenter.getInterviewsInternet();
    } else {
      mPresenter.getInterviewsLocal();
    }
    return v;
  }


  @Override
  public void hideProgress() {
  }


  @Override
  public void showProgress() {

  }

  @Override
  public void dismissProgress() {

  }


  @Override
  public void showInterviews(List<Interview> interviews) {

    InterviewsAdapter adapter = new InterviewsAdapter(interviews, this);
    mRecyclerView.setAdapter(adapter);

  }

  @Override
  public void showMessage(String t) {

  }

  @Override
  public void showInterviewDetailUi(int interviewId) {
    Intent intent = new Intent(getActivity(), InterviewDetail.class);
    intent.putExtra("interview_id", interviewId);
    startActivity(intent);
  }

  @Override
  public boolean hasInternetConnection(Context context) {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (connectivityManager != null) {
      NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
      return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    return false;
  }

  @Override
  public void showNoInterviews() {

  }

  @Override
  public void onItemClick(Interview item) {
    mPresenter.onInterviewItemClick(item);
  }
}