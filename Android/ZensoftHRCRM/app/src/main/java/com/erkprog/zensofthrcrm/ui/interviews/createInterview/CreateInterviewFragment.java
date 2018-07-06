package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

public class CreateInterviewFragment extends Fragment implements CreateInterviewContract.View, View
    .OnClickListener {
  private static final String TAG = "CREATE INTRVW FRAGMENT";

  public static final String CANDIDATE_ID = "candidate id";
  public static final String CANDIDATE_LASTNAME = "candidate last name";
  public static final String CANDIDATE_FIRSTNAME = "candidate first name";
  public static final String CANDIDATE_DEPARTMENT = "candidate department";
  private static final String DIALOG = "Date and time dialog";
  public static final int REQUEST_DATE_CODE = 7;

  private CreateInterviewContract.Presenter mPresenter;

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

    mPresenter = new CreateInterviewPresenter(this);
    mInterviewDate = new Date();
    initUI(v);
    setFields();

    Log.d(TAG, "onCreateView: " + mCandidateId);

    return v;
  }

  private void initUI(View v) {
    mCandidateName = v.findViewById(R.id.crint_candidate_name);
    mDepartmentName = v.findViewById(R.id.crint_department);
    mDate = v.findViewById(R.id.crint_date);
    mInterviewers = v.findViewById(R.id.crint_interviewers);
    mSetDateButton = v.findViewById(R.id.crint_set_date_button);
    mSetDateButton.setOnClickListener(this);
    mAddInterviewersButton = v.findViewById(R.id.crint_add_image);
    mAddInterviewersButton.setOnClickListener(this);
    mCreateButton = v.findViewById(R.id.crint_create_button);
    mCreateButton.setOnClickListener(this);
  }

  private void setFields() {
    Bundle args = getArguments();
    StringBuilder candidateName = new StringBuilder();
    candidateName.append(args.getString(CANDIDATE_FIRSTNAME))
        .append(" ")
        .append(args.getString(CANDIDATE_LASTNAME));
    mCandidateName.setText(candidateName);
    mDepartmentName.setText(args.getString(CANDIDATE_DEPARTMENT));
    mCandidateId = args.getInt(CANDIDATE_ID);
    mDate.setText(mInterviewDate.toString());
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
  public void startDatePicker() {
    FragmentManager fm = getFragmentManager();
    DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mInterviewDate);
    datePickerFragment.setTargetFragment(CreateInterviewFragment.this, REQUEST_DATE_CODE);
    datePickerFragment.show(fm, DIALOG);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.crint_set_date_button:
        mPresenter.onSetDateButtonClick();
        break;

      case R.id.crint_add_image:
        //TODO: display list of available interviewers here
        break;

      case R.id.crint_create_button:
        //TODO: create new interview here (implement POST request)
        break;

      default:
        break;
    }
  }

  @Override
  public void showToast(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode != Activity.RESULT_OK) {
      return;
    }

    if (requestCode == REQUEST_DATE_CODE) {
      //New date and time for interview sucessfully received from DatePickerFragment
      Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
      updateInterviewDate(date);

    }
  }

  private void updateInterviewDate(Date date) {
    mInterviewDate = date;
    mDate.setText(mInterviewDate.toString());
  }
}
