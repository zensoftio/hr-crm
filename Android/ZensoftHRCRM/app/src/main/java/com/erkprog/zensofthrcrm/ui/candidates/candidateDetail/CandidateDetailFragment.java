package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.annotation.SuppressLint;
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
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.data.entity.User;
import com.erkprog.zensofthrcrm.data.network.candidates.CandidatesRepository;
import com.erkprog.zensofthrcrm.ui.interviews.createInterview.CreateInterview;

import java.util.ArrayList;
import java.util.List;

public class CandidateDetailFragment extends Fragment implements CandidateDetailContract.View,
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

  private void addAdapterViews(final BaseAdapter adapter) {
    int itemsCount = adapter.getCount();

    if (itemsCount > 0) {
      TextView descriptionText = new TextView(getActivity());
      descriptionText.setText(getTitleText(adapter));
      descriptionText.setTextColor(getResources().getColor(R.color.main_attributes));
      mLayout.addView(descriptionText);

      for (int i = 0; i < adapter.getCount(); i++) {
        View item = adapter.getView(i, null, null);
        final int finalI = i;
        item.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (adapter instanceof CvsAdapter) {
              Cv cvItem = (Cv) adapter.getItem(finalI);
              onCvItemClicked(cvItem);

            } else if (adapter instanceof CommentsAdapter) {
              Comment commentItem = (Comment) adapter.getItem(finalI);
              onCommentItemClicked(commentItem);

            } else if (adapter instanceof InterviewsAdapter) {
              CandidateInterviewItem interviewItem = (CandidateInterviewItem) adapter.getItem(finalI);
              onInterviewItemClicked(interviewItem);

            }
          }
        });
        mLayout.addView(item);
      }
    }

  }

  private int getTitleText(BaseAdapter adapter) {
    if (adapter instanceof CvsAdapter) {
      return R.string.cvs;
    } else if (adapter instanceof CommentsAdapter) {
      return R.string.comments;
    } else if (adapter instanceof InterviewsAdapter) {
      return R.string.interviews;
    }
    return -1;
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

  @SuppressLint("LongLogTag")
  @Override
  public void startCreateInterview(Candidate candidate) {
    Log.d(TAG, "startCreateInterview: " + candidate.getId());
    Intent intent = CreateInterview.newIntent(getActivity(), candidate);
    startActivity(intent);
  }

  public static CandidateDetailFragment newInstance(@Nullable int candidateId) {
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

  class CvsAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Cv> mCvsList;

    CvsAdapter(Context context, List<Cv> cvsList) {
      mContext = context;
      mCvsList = cvsList;
      mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<Cv> newData) {
      mCvsList = newData;
      this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
      return mCvsList.size();
    }

    @Override
    public Object getItem(int position) {
      return mCvsList.get(position);
    }

    @Override
    public long getItemId(int position) {
      return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View v = mLayoutInflater.inflate(R.layout.cd_cvs_item, parent, false);
      TextView attachment = v.findViewById(R.id.cv_item_attachment);
      TextView createdDate = v.findViewById(R.id.cv_item_created_text);

      Cv cvItem = (Cv) getItem(position);
      attachment.setText("attachment" + String.valueOf(position + 1));
      createdDate.setText(cvItem.getCreated().toString());

      return v;
    }
  }


  class CommentsAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Comment> mCommentsList;

    CommentsAdapter(Context context, List<Comment> data) {
      mContext = context;
      mCommentsList = data;
      mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<Comment> newData) {
      mCommentsList = newData;
      this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
      return mCommentsList.size();
    }

    @Override
    public Object getItem(int position) {
      return mCommentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
      return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View v = mLayoutInflater.inflate(R.layout.cd_comment_item, parent, false);
      TextView createdBy = v.findViewById(R.id.cd_comment_username);
      TextView date = v.findViewById(R.id.cd_comment_date);
      TextView text = v.findViewById(R.id.cd_comment_text);

      Comment comment = (Comment) getItem(position);
      User user = comment.getCreatedBy();
      createdBy.setText(user.getFirstName() + " " + user.getLastName());
      date.setText(comment.getCreated().toString());
      text.setText(comment.getText());

      return v;
    }
  }


  class InterviewsAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<CandidateInterviewItem> mInterviews;

    InterviewsAdapter(Context context, List<CandidateInterviewItem> data) {
      mContext = context;
      mInterviews = data;
      mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<CandidateInterviewItem> newData) {
      mInterviews = newData;
      this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
      return mInterviews.size();
    }

    @Override
    public Object getItem(int position) {
      return mInterviews.get(position);
    }

    @Override
    public long getItemId(int position) {
      return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View v = mLayoutInflater.inflate(R.layout.cd_interview_item, parent, false);
      TextView status = v.findViewById(R.id.cd_interview_status);
      TextView date = v.findViewById(R.id.cd_interview_date);
      TextView interviewersText = v.findViewById(R.id.cd_interview_interviewers);

      CandidateInterviewItem interview = (CandidateInterviewItem) getItem(position);
      status.setText(interview.getStatus().toString());
      date.setText(interview.getDate());
      List<User> interviewers = interview.getInterviewers();
      StringBuilder users = new StringBuilder();
      users.append("interviewers:\n");
      for (User interviewer : interviewers) {
        String firstName =
            interviewer.getFirstName() != null ? interviewer.getFirstName() : "";
        String lastName =
            interviewer.getLastName() != null ? interviewer.getLastName() : "";
        users.append("-").append(firstName).append(" ").append(lastName).append("\n");
      }
      interviewersText.setText(users);

      return v;
    }
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
}
