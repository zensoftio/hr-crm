package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.ui.interviews.createInterview.CreateInterview;

import java.util.List;

public class CandidateDetailFragment extends Fragment implements CandidateDetailContract.View,
    View.OnClickListener {
  private static final String TAG = "PROFILE DETAILS";
  public static final String ARGUMENT_CANDIDATE_ID = "argument candidate id";
  private static final int NO_ID = -1;
  private CandidateDetailContract.Presenter mPresenter;
  private int mCandidateId;
  private LinearLayout mLayout;
  private ProgressBar mProgressBar;
  private TextView mFirstName;
  private TextView mLastName;
  private TextView mEmail;
  private TextView mPhoneNumber;
  private TextView mDepartment;
  private TextView mYearsOfExp;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initPresenter();
  }

  private void initPresenter() {
    mPresenter = new CandidateDetailPresenter(requireContext(), CRMApplication.getInstance(requireContext())
        .getApiService());
    mPresenter.bind(this);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_candidate_detail, container, false);
    initUI(v);
    if (getArguments() != null) {
      mCandidateId = getArguments().getInt(ARGUMENT_CANDIDATE_ID);
    } else {
      mCandidateId = NO_ID;
    }
    return v;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (mCandidateId != NO_ID) {
      mPresenter.loadCandidateInfo(mCandidateId);
    }
  }

  private void initUI(View v) {
    mLayout = v.findViewById(R.id.cd_root_layout);
    mFirstName = v.findViewById(R.id.cd_first_name);
    mLastName = v.findViewById(R.id.cd_last_name);
    mEmail = v.findViewById(R.id.cd_email);
    mPhoneNumber = v.findViewById(R.id.cd_phone);
    mDepartment = v.findViewById(R.id.cd_department);
    mYearsOfExp = v.findViewById(R.id.cd_years_xp);
    mProgressBar = v.findViewById(R.id.cd_progress_bar);
    dismissProgress();
  }

  @Override
  public void showCandidateDetails(Candidate candidate) {
    mFirstName.setText(candidate.getFirstName());
    mLastName.setText(candidate.getLastName());
    mEmail.setText(candidate.getEmail());
    mPhoneNumber.setText(candidate.getPhone());
    if (candidate.getPosition() != null && candidate.getPosition().getDepartment() != null) {
      mDepartment.setText(candidate.getPosition().getDepartment().getName());
    }
    mYearsOfExp.setText(String.valueOf(candidate.getExperience()));
    addExtraViews(candidate);
  }

  private void addExtraViews(Candidate candidate) {
    //add CV views to layout
    addCvsViews(candidate.getCvs());
    //add Comment views to layout
    addCommentViews(candidate.getComments());
    //add Interview views to layout
    addInterviewViews(candidate.getInterviews());
    //add Buttons
    addActionButtons();
  }

  private void addInterviewViews(List<Interview> interviewList) {
    if (interviewList == null) {
      return;
    }
    int itemsCount = interviewList.size();
    if (itemsCount > 0) {
      // add description Textview
      TextView descriptionText = new TextView(getActivity());
      descriptionText.setText(R.string.interviews);
      descriptionText.setTextColor(getResources().getColor(R.color.colorBlack));
      mLayout.addView(descriptionText);
      // add interview views
      for (final Interview interviewItem : interviewList) {
        View interviewView = ViewBuilder.createInterviewView(getActivity(), interviewItem);
        interviewView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            mPresenter.onInterviewItemClicked(interviewItem);
          }
        });
        mLayout.addView(interviewView);
      }
    }
  }

  private void addCommentViews(List<Comment> commentList) {
    if (commentList == null) {
      return;
    }
    int itemsCount = commentList.size();
    if (itemsCount > 0) {
      // add description Textview
      TextView descriptionText = new TextView(getActivity());
      descriptionText.setText(R.string.comments);
      descriptionText.setTextColor(getResources().getColor(R.color.colorBlack));
      mLayout.addView(descriptionText);
      // add comment views
      for (final Comment commentItem : commentList) {
        View commentView = ViewBuilder.createCommentView(getActivity(), commentItem);
        mLayout.addView(commentView);
      }
    }
  }

  private void addCvsViews(List<Cv> cvList) {
    if (cvList == null) {
      return;
    }
    int itemsCount = cvList.size();
    if (itemsCount > 0) {
      // add description Textview
      TextView descriptionText = new TextView(getActivity());
      descriptionText.setText(R.string.cvs);
      descriptionText.setTextColor(getResources().getColor(R.color.colorBlack));
      mLayout.addView(descriptionText);
      // add cvs views
      for (final Cv cvItem : cvList) {
        View cvView = ViewBuilder.createCvView(getActivity(), cvItem, cvList.indexOf(cvItem));
        cvView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            mPresenter.onCvItemClicked(cvItem);
          }
        });
        mLayout.addView(cvView);
      }
    }
  }

  private void addActionButtons() {
    View v = LayoutInflater.from(getActivity()).inflate(R.layout.cd_action_buttons, null, false);
    v.findViewById(R.id.cd_interview_button).setOnClickListener(this);
    v.findViewById(R.id.cd_edit_button).setOnClickListener(this);
    v.findViewById(R.id.cd_delete_button).setOnClickListener(this);
    v.findViewById(R.id.cd_message_button).setOnClickListener(this);
    mLayout.addView(v);
  }

  @Override
  public void startCreateInterview(Candidate candidate) {
    Log.d(TAG, "startCreateInterview: " + candidate.getId());
    Intent intent = CreateInterview.newIntent(getActivity(), candidate);
    startActivity(intent);
  }

  public static CandidateDetailFragment newInstance(int candidateId) {
    Bundle arguments = new Bundle();
    arguments.putInt(ARGUMENT_CANDIDATE_ID, candidateId);
    CandidateDetailFragment fragment = new CandidateDetailFragment();
    fragment.setArguments(arguments);
    return fragment;
  }

  @Override
  public void showMessage(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public boolean hasInternetConnection(Context context) {
    return false;
  }

  @Override
  public void showProgress() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void dismissProgress() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.cd_interview_button:
        mPresenter.onCreateInterviewClicked();
        break;
      case R.id.cd_delete_button:
        //TODO: implement profile deleting
        showMessage("Delete candidate profile");
        break;
      case R.id.cd_edit_button:
        //TODO: implement profile editing
        showMessage("Edit profile");
        break;
      case R.id.cd_message_button:
        //TODO: implement sending message
        showMessage("Send message");
        break;
      default:
        break;
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mPresenter.unbind();
  }
}