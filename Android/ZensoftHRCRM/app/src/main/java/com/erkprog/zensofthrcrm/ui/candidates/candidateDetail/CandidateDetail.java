package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.erkprog.zensofthrcrm.R;

public class CandidateDetail extends AppCompatActivity {
  private static final String TAG = "CANDIDATE DETAIL";

  private static final String EXTRA_CANDIDATE_ID = "candidate_id";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "onCreate: starts");
    setContentView(R.layout.activity_candidate_detail);

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.candidate_detail_container);

    int candidateId = getIntent().getIntExtra(EXTRA_CANDIDATE_ID, -1);

    if (fragment == null) {
      fragment = CandidateDetailFragment.newInstance(candidateId);
      fm.beginTransaction()
          .add(R.id.candidate_detail_container, fragment)
          .commit();
    }

  }

  public static Intent getIntent(Context context, int candidateId) {
    Intent intent = new Intent(context, CandidateDetail.class);
    intent.putExtra(EXTRA_CANDIDATE_ID, candidateId);
    return intent;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy");
  }
}
