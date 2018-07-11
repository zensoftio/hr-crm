package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.erkprog.zensofthrcrm.R;

public class InterviewDetail extends AppCompatActivity {

  private static final String EXTRA_INTERVIEW_ID = "interview_id";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_interview_detail);

    Bundle args = new Bundle();
    args.putInt(EXTRA_INTERVIEW_ID, getIntent().getExtras().getInt(EXTRA_INTERVIEW_ID));

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.interview_detail_container);

    if (fragment == null) {
      fragment = new InterviewDetailFragment();
      fragment.setArguments(args);
      fm.beginTransaction()
          .add(R.id.interview_detail_container, fragment)
          .commit();
    }

    if (getSupportActionBar() != null)
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
