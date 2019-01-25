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
import android.widget.AdapterView;
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

public class InterviewersFragment extends DialogFragment {
  private static final String TAG = "ADD INTERVIEWERS";

  public static final String USERS = "result users";

  private ListView mListView;
  private ProgressBar mProgressBar;
  private ApiInterface mApiService;
  private InterviewersAdapter mAdapter;
  private ArrayList<User> resultUsers;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mApiService = CRMApplication.getInstance(requireContext()).getApiService();
    resultUsers = new ArrayList<>();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_interviewers, null);
    mListView = v.findViewById(R.id.add_intrvw_listview);
    mProgressBar = v.findViewById(R.id.intrvw_progress_bar);

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InterviewerItem item = mAdapter.getItem(position);
        if (item != null) {
          item.setChecked(!item.isChecked());
        }
        mAdapter.notifyDataSetChanged();
      }
    });

    mApiService.getUsers().enqueue(new Callback<UsersResponse>() {
      @Override
      public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
        if (getContext() != null && response.isSuccessful()) {
          mProgressBar.setVisibility(View.GONE);
          ArrayList<InterviewerItem> interviewers = getInterviewers(response.body());

          mAdapter = new InterviewersAdapter(getContext(), interviewers);
          mListView.setAdapter(mAdapter);
        }
      }

      @Override
      public void onFailure(Call<UsersResponse> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getMessage());
      }
    });

    return new AlertDialog.Builder(getActivity())
        .setView(v)
        .setTitle(R.string.add_interviewers)
        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

            ArrayList<User> resultUsers = getResult();

            sendResult(Activity.RESULT_OK, resultUsers);
          }
        })
        .create();
  }

  private ArrayList<InterviewerItem> getInterviewers(UsersResponse response) {
    ArrayList<InterviewerItem> interviewers = new ArrayList<>();

    for (User user : response.getUsers()) {
      interviewers.add(new InterviewerItem(user));
    }

    return interviewers;
  }

  private ArrayList<User> getResult() {
    ArrayList<User> resultUsers = new ArrayList<>();
    if (mAdapter != null) {
      for (int i = 0; i < mAdapter.getCount(); i++) {
        if (mAdapter.getItem(i) != null && mAdapter.getItem(i).isChecked()) {
          resultUsers.add(mAdapter.getItem(i).getUser());
        }
      }
    }

    return resultUsers;
  }

  public static InterviewersFragment newInstance() {
    return new InterviewersFragment();
  }

  private void sendResult(int resultCode, ArrayList<User> result) {
    if (getTargetFragment() == null) {
      return;
    }

    Intent intent = new Intent();
    intent.putExtra(USERS, result);
    getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
  }
}
