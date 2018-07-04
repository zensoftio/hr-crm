package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.R;

import java.util.Date;

public class CreateInterviewFragment extends Fragment implements View.OnClickListener {
  private static final String TAG = "CREATE INTRVW FRAGMENT";

  public static final String CANDIDATE_ID = "candidate id";
  public static final String CANDIDATE_LASTNAME = "candidate last name";
  public static final String CANDIDATE_FIRSTNAME = "candidate first name";
  public static final String CANDIDATE_DEPARTMENT = "candidate department";

  private TextView mCandidateName;
  private TextView mDepartmentName;
  private TextView mDate;
  private TextView mInterviewers;
  private Button mSetDateButton;
  private ImageView mAddInterviewersButton;
  private Button mCreateButton;

  private Date mInterviewDate;
  private int mCandidateId;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_create_interview, container, false);

    mInterviewDate = new Date();
    initUI(v);

    Log.d(TAG, "onCreateView: " + mCandidateId);


    return v;
  }

  private void initUI(View v) {
    Bundle args = getArguments();
    mCandidateName = v.findViewById(R.id.crint_candidate_name);
    mCandidateName.setText(args.getString(CANDIDATE_FIRSTNAME) + " " + args.getString(CANDIDATE_LASTNAME));
    mDepartmentName = v.findViewById(R.id.crint_department);
    mDepartmentName.setText(args.getString(CANDIDATE_DEPARTMENT));
    mCandidateId = args.getInt(CANDIDATE_ID);
    mDate = v.findViewById(R.id.crint_date);
    mDate.setText(mInterviewDate.toString());
    mInterviewers = v.findViewById(R.id.crint_interviewers);
    mSetDateButton = v.findViewById(R.id.crint_set_date_button);
    mSetDateButton.setOnClickListener(this);
    mAddInterviewersButton = v.findViewById(R.id.crint_add_image);
    mAddInterviewersButton.setOnClickListener(this);
    mCreateButton = v.findViewById(R.id.crint_create_button);
    mCreateButton.setOnClickListener(this);
  }

  private void updateUi() {
  }

  public static CreateInterviewFragment newInstance(int candidateId, String lastName, String
      firstname, String departmentName) {
    Bundle arguments = new Bundle();
    arguments.putInt(CANDIDATE_ID, candidateId);
    arguments.putString(CANDIDATE_LASTNAME, lastName);
    arguments.putString(CANDIDATE_FIRSTNAME, firstname);
    arguments.putString(CANDIDATE_DEPARTMENT, departmentName);
    CreateInterviewFragment fragment = new CreateInterviewFragment();
    fragment.setArguments(arguments);
    return fragment;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.crint_set_date_button:
        break;

      case R.id.crint_add_image:
        break;

      case R.id.crint_create_button:
        break;

      default:
        break;
    }

  }
}
