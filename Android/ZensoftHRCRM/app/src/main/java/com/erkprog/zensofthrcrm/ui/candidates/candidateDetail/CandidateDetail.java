package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erkprog.zensofthrcrm.R;

public class CandidateDetail extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_candidate_detail);

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.candidate_detail_container);

    if (fragment == null) {
      fragment = new CandidateDetailFragment();
      fm.beginTransaction()
          .add(R.id.candidate_detail_container, fragment)
          .commit();
    }

  }
}
