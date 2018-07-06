package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

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
import android.widget.TextView;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.data.network.candidates.CandidatesRepository;
import com.erkprog.zensofthrcrm.ui.BaseFragment;
import com.erkprog.zensofthrcrm.ui.interviews.createInterview.CreateInterview;

import java.util.ArrayList;

public class CandidateDetailFragment extends BaseFragment implements CandidateDetailContract.View,
    View.OnClickListener {
  private static final String TAG = "PROFILE DETAILS";
  public static final String ARGUMENT_CANDIDATE_ID = "argument candidate id";

  private CandidateDetailContract.Presenter mPresenter;

  private LinearLayout mLayout;

  private CvsAdapter mCvsAdapter;
  private CommentsAdapter mCommentsAdapter;
  private InterviewsAdapter mInterviewsAdapter;

  private TextView mFirstName;
  private TextView mLastName;
  private TextView mEmail;
  private TextView mPhoneNumber;
  private TextView mDepartment;
  private TextView mYearsOfExp;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_candidate_detail, container, false);
    initUI(v);
    int candidateId = getArguments().getInt(ARGUMENT_CANDIDATE_ID);
    showToast(String.valueOf(candidateId));
    mPresenter = new CandidateDetailPresenter(this, new CandidatesRepository(getActivity()));
    mPresenter.loadCandidateInfo(candidateId);
    return v;
  }

  private void initUI(View v) {
    mLayout = v.findViewById(R.id.cd_root_layout);
    mFirstName = v.findViewById(R.id.cd_first_name);
    mLastName = v.findViewById(R.id.cd_last_name);
    mEmail = v.findViewById(R.id.cd_email);
    mPhoneNumber = v.findViewById(R.id.cd_phone);
    mDepartment = v.findViewById(R.id.cd_department);
    mYearsOfExp = v.findViewById(R.id.cd_years_xp);

    mCvsAdapter = new CvsAdapter(getActivity(), new ArrayList<Cv>());
    mCommentsAdapter = new CommentsAdapter(getActivity(), new ArrayList<Comment>());
    mInterviewsAdapter = new InterviewsAdapter(getActivity(), new ArrayList<CandidateInterviewItem>());
  }

  @Override
  public void showCandidateDetails(Candidate candidate) {
    mFirstName.setText(candidate.getFirstName());
    mLastName.setText(candidate.getLastName());
    mEmail.setText(candidate.getEmail());
    mPhoneNumber.setText(candidate.getPhone());
    mDepartment.setText(candidate.getPosition().getDepartmentModel().getName());
    mYearsOfExp.setText(candidate.getExperience().toString());
    mCvsAdapter.setData(candidate.getCvs());
    mCommentsAdapter.setData(candidate.getComments());
    mInterviewsAdapter.setData(candidate.getInterviews());
    addViewsToLayout();
  }

  private void addViewsToLayout() {
    //add CV views to layout
    addCvsViews(mCvsAdapter);

    //add Comment views to layout
    addCommentViews(mCommentsAdapter);

    //add Interview views to layout
    addInterviewViews(mInterviewsAdapter);

    //add Buttons
    addActionButtons();

  }

  private void addInterviewViews(final InterviewsAdapter interviewsAdapter) {
    int itemsCount = interviewsAdapter.getCount();

    if (itemsCount > 0) {

      // add description Textview
      TextView descriptionText = new TextView(getActivity());
      descriptionText.setText(R.string.interviews);
      descriptionText.setTextColor(getResources().getColor(R.color.main_attributes));
      mLayout.addView(descriptionText);

      // add interview views
      for (int i = 0; i < itemsCount; i++) {
        View item = interviewsAdapter.getView(i, null, null);
        final int finalI = i;

        item.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            CandidateInterviewItem interviewItem = (CandidateInterviewItem) interviewsAdapter.getItem(finalI);
            onInterviewItemClicked(interviewItem);
          }
        });
        mLayout.addView(item);
      }
    }
  }

  private void addCommentViews(final CommentsAdapter commentsAdapter) {
    int itemsCount = commentsAdapter.getCount();

    if (itemsCount > 0) {

      // add description Textview
      TextView descriptionText = new TextView(getActivity());
      descriptionText.setText(R.string.comments);
      descriptionText.setTextColor(getResources().getColor(R.color.main_attributes));
      mLayout.addView(descriptionText);

      // add comment views
      for (int i = 0; i < itemsCount; i++) {
        View item = commentsAdapter.getView(i, null, null);
        final int finalI = i;

        item.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Comment commentItem = (Comment) commentsAdapter.getItem(finalI);
            onCommentItemClicked(commentItem);
          }
        });
        mLayout.addView(item);
      }
    }
  }

  private void addCvsViews(final CvsAdapter cvsAdapter) {
    int itemsCount = cvsAdapter.getCount();

    if (itemsCount > 0) {

      // add description Textview
      TextView descriptionText = new TextView(getActivity());
      descriptionText.setText(R.string.cvs);
      descriptionText.setTextColor(getResources().getColor(R.color.main_attributes));
      mLayout.addView(descriptionText);

      // add cvs views
      for (int i = 0; i < itemsCount; i++) {
        View item = cvsAdapter.getView(i, null, null);
        final int finalI = i;

        item.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Cv cvItem = (Cv) cvsAdapter.getItem(finalI);
              onCvItemClicked(cvItem);
          }
        });
        mLayout.addView(item);
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

  private void onInterviewItemClicked(CandidateInterviewItem interviewItem) {
    mPresenter.onInterviewItemClicked(interviewItem);
  }

  private void onCommentItemClicked(Comment commentItem) {
    mPresenter.onCommentItemClicked(commentItem);
  }

  private void onCvItemClicked(Cv cvItem) {
    mPresenter.onCvItemClicked(cvItem);
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
  public boolean isActive() {
    return isAdded();
  }

  @Override
  public void showLoadingCandidateError() {

  }

  @Override
  public void showToast(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

  }

  @Override
  public void onClick(View v) {

    switch (v.getId()) {
      case R.id.cd_interview_button:
        mPresenter.onCreateInterviewClicked();
        break;

      case R.id.cd_delete_button:
        showToast("Delete candidate profile");
        break;

      case R.id.cd_edit_button:
        showToast("Edit profile");
        break;

      case R.id.cd_message_button:
        showToast("Send message");
        break;

      default:
        break;
    }
  }

  @Override
  protected String setTitle() {
    return getString(R.string.candidate_profile);
  }
}
