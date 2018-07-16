package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.editCandidate;

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
import com.erkprog.zensofthrcrm.ui.interviews.interviewsList.InterviewsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditCandidateFragment extends Fragment implements EditCandidateContract.View {

  private EditCandidateContract.Presenter mPresenter;

  @BindView(R.id.ec_progress_bar)
  ProgressBar mProgressBar;

  @BindView(R.id.ec_first_name)
  TextView mFirstName;

  @BindView(R.id.ec_last_name)
  TextView mLastName;

  @BindView(R.id.ec_department)
  TextView mDepartment;

  @BindView(R.id.ec_email)
  TextView mEmail;

  @BindView(R.id.ec_exp)
  TextView mExp;

  @BindView(R.id.ec_phone)
  TextView mPhone;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = new EditCandidatePresenter(this, CRMApplication.getInstance(requireContext())
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
    View v = inflater.inflate(R.layout.fragment_edit_candidate, container, false);

    ButterKnife.bind(this, v);

    dismissProgress();

    if (hasInternetConnection(v.getContext())) {

    } else {

    }

    return v;
  }

  @Override
  public void dismissProgress() {
    mProgressBar.setVisibility(View.GONE);
  }


  @Override
  public void showMessage(String message) {
    dismissProgress();
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
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
  public void showData() {

  }

}