package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;

public class CreateInterviewFragment extends Fragment {

  public static final String CANDIDATE_ID = "candidate id";
  public static final String CANDIDATE_LASTNAME = "candidate last name";
  public static final String CANDIDATE_FIRSTNAME = "candidate first name";



  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_create_interview, container, false);

    return v;
  }

  public static CreateInterviewFragment newInstance(Candidate candidate){
    Bundle arguments = new Bundle();
    arguments.putInt(CANDIDATE_ID, candidate.getId());
    arguments.putString(CANDIDATE_LASTNAME, candidate.getLastName() != null ? candidate
        .getLastName() : "");
    arguments.putString(CANDIDATE_FIRSTNAME, candidate.getFirstName() != null ? candidate
        .getFirstName() : "");
    CreateInterviewFragment fragment = new CreateInterviewFragment();
    fragment.setArguments(arguments);
    return fragment;
  }
}
