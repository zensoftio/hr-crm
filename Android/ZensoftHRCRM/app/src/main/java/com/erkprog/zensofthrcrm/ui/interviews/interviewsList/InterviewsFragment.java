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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.ui.ItemClickListener;
import com.erkprog.zensofthrcrm.ui.interviews.interviewDetail.InterviewDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InterviewsFragment extends Fragment implements InterviewsContract.View,
    ItemClickListener<Interview> {

  private InterviewsContract.Presenter mPresenter;

  @BindView(R.id.recycler_view_all_interviews)
  RecyclerView mRecyclerView;
  @BindView(R.id.interviews_progress_bar)
  ProgressBar mProgressBar;
  @BindView(R.id.txt_empty_interviews_view)
  TextView noInterviewsView;


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

    ButterKnife.bind(this, v);

    mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_all_interviews);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(v.getContext());
    mRecyclerView.setLayoutManager(layoutManager);

    showProgress();
    if (hasInternetConnection(v.getContext())) {
      mPresenter.getInterviewsInternet();
    } else {
      mPresenter.getInterviewsLocal();
    }
    return v;
  }

  @Override
  public void dismissProgress() {
    mProgressBar.setVisibility(View.GONE);
  }


  @Override
  public void showInterviews(List<Interview> interviews) {
    dismissProgress();

    if (interviews.size() > 0) {
      noInterviewsView.setVisibility(View.GONE);
      InterviewsAdapter adapter = new InterviewsAdapter(interviews, this);
      mRecyclerView.setAdapter(adapter);
    } else
      noInterviewsView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showMessage(String message) {
    dismissProgress();
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
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
  public void showProgress() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void onItemClick(Interview item) {
    mPresenter.onInterviewItemClick(item);
  }
}