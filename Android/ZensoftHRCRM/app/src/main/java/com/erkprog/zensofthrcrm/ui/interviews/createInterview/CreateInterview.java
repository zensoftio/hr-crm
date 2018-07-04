package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;

public class CreateInterview extends AppCompatActivity {
  private static final String TAG = "mylog:CreateInterview";

  private static final String EXTRA_CANDIDATE_ID = "create interview extra candidate id";
  private static final String EXTRA_CANDIDATE_LASTNAME = "create interview extra lastname";
  private static final String EXTRA_CANDIDATE_FIRSTNAME = "create interview extra firstname";
  private static final String EXTRA_CANDIDATE_DEPARTMENT = "create interview extra department";
//  private static final String EXTRA_CANDIDATE = "create interview extra candidate";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_interview);

    FragmentManager fm = getSupportFragmentManager();

    Fragment fragment = fm.findFragmentById(R.id.create_interview_fragment_container);
    if (fragment == null) {
      int candidateId = getIntent().getIntExtra(EXTRA_CANDIDATE_ID, -1);
      Log.d(TAG, "onCreate: " + candidateId);
      String lastName = getIntent().getStringExtra(EXTRA_CANDIDATE_LASTNAME);
      String firstName = getIntent().getStringExtra(EXTRA_CANDIDATE_FIRSTNAME);
      String department = getIntent().getStringExtra(EXTRA_CANDIDATE_DEPARTMENT);
      fragment = CreateInterviewFragment.newInstance(candidateId,lastName, firstName, department);
      fm.beginTransaction()
          .add(R.id.create_interview_fragment_container, fragment)
          .commit();
    }

  }

  public static Intent newIntent(Context context, Candidate candidate) {
    Intent intent = new Intent(context, CreateInterview.class);
    intent.putExtra(EXTRA_CANDIDATE_ID, candidate.getId());
    intent.putExtra(EXTRA_CANDIDATE_LASTNAME, candidate.getLastName());
    intent.putExtra(EXTRA_CANDIDATE_FIRSTNAME, candidate.getFirstName());
    intent.putExtra(EXTRA_CANDIDATE_DEPARTMENT, candidate.getPosition().getDepartmentModel()
        .getName());
    return intent;
  }
}
