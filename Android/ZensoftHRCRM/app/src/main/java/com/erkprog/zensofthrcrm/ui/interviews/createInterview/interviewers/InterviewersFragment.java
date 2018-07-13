package com.erkprog.zensofthrcrm.ui.interviews.createInterview.interviewers;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.erkprog.zensofthrcrm.R;

public class InterviewersFragment extends DialogFragment{

  private ListView mListView;
  private ProgressBar mProgressBar;

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    View v  = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_interviewers, null);
    mListView = v.findViewById(R.id.add_intrvw_listview);
    mProgressBar = v.findViewById(R.id.intrvw_progress_bar);

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
