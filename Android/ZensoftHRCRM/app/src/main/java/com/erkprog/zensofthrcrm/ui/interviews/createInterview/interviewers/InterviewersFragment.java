package com.erkprog.zensofthrcrm.ui.interviews.createInterview.interviewers;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.User;
import com.erkprog.zensofthrcrm.data.entity.UsersResponse;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterviewersFragment extends DialogFragment{
  private static final String TAG = "InterviewersFragment";

  private ListView mListView;
  private ProgressBar mProgressBar;
  private ApiInterface mApiService;
  private InterviewersAdapter mAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mApiService = CRMApplication.getInstance(requireContext()).getApiService();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    View v  = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_interviewers, null);
    mListView = v.findViewById(R.id.add_intrvw_listview);
    mProgressBar = v.findViewById(R.id.intrvw_progress_bar);

    mApiService.getUsers().enqueue(new Callback<UsersResponse>() {
      @Override
      public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
        mProgressBar.setVisibility(View.GONE);
        ArrayList<InterviewerItem> interviewers = new ArrayList<>();

        for (User user: response.body().getUsers()) {
          interviewers.add(new InterviewerItem(user, false));
        }

        mAdapter = new InterviewersAdapter(requireContext(), interviewers);
        mListView.setAdapter(mAdapter);
      }

      @Override
      public void onFailure(Call<UsersResponse> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getMessage());
      }
    });

    return new AlertDialog.Builder(getActivity())
        .setView(v)
        .setTitle("Add interviewers")
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            sendResult(Activity.RESULT_OK);
          }
        })
        .create();
  }


  public static InterviewersFragment newInstance() {
    return new InterviewersFragment();
  }

  private void sendResult(int resultCode) {
    if (getTargetFragment() == null) {
      return;
    }

    Intent intent = new Intent();
    getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
  }
}
