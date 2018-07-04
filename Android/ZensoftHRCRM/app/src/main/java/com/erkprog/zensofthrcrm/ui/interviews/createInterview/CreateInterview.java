package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erkprog.zensofthrcrm.R;

public class CreateInterview extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_interview);

    FragmentManager fm = getSupportFragmentManager();

    Fragment fragment = fm.findFragmentById(R.id.create_interview_fragment_container);
    if (fragment == null) {
      fragment = new CreateInterviewFragment();
      fm.beginTransaction()
          .add(R.id.create_interview_fragment_container, fragment)
          .commit();
    }

  }
}
