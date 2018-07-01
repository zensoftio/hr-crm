package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.User;

import java.util.ArrayList;
import java.util.List;

public class CandidateDetailFragment extends Fragment implements CandidateDetailContract.View {

  private CandidateDetailContract.Presenter mPresenter;
  private ListView mCvsListView;
  private ListView mCommentsListView;
  private ListView mInterviewsListView;

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
    mPresenter = new CandidateDetailPresenter(this);
    initUI(v);
    return v;

  }

  private void initUI(View v) {
    mFirstName = v.findViewById(R.id.cd_first_name);
    mLastName = v.findViewById(R.id.cd_last_name);
    mEmail = v.findViewById(R.id.cd_email);
    mPhoneNumber = v.findViewById(R.id.cd_phone);
    mDepartment = v.findViewById(R.id.cd_department);
    mYearsOfExp = v.findViewById(R.id.cd_years_xp);

    mCvsListView = v.findViewById(R.id.cd_cvs_listview);
    mCvsAdapter = new CvsAdapter(getActivity(), new ArrayList<Cv>());
    mCvsListView.setAdapter(mCvsAdapter);

    mCommentsListView = v.findViewById(R.id.cd_comments_listview);
    mCommentsAdapter = new CommentsAdapter(getActivity(), new ArrayList<Comment>());
    mCommentsListView.setAdapter(mCommentsAdapter);

    mInterviewsListView = v.findViewById(R.id.cd_interviews_listview);
    mInterviewsAdapter = new InterviewsAdapter(getActivity(), new ArrayList<CandidateInterviewItem>());
    mInterviewsListView.setAdapter(mInterviewsAdapter);
  }

  @Override
  public void showCandidateDetails(Candidate candidate) {

  }

  @Override
  public void showLoadingCandidateError() {

  }

  @Override
  public void showToast(String message) {

  }

  class CvsAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Cv> mCvsList;

    CvsAdapter(Context context, ArrayList<Cv> cvsList) {
      mContext = context;
      mCvsList = cvsList;
      mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(ArrayList<Cv> newData) {
      mCvsList = newData;
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
      attachment.setText(R.string.attachment + position);
      createdDate.setText(cvItem.getCreated().toString());

      return v;
    }
  }


  class CommentsAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Comment> mCommentsList;

    CommentsAdapter(Context context, ArrayList<Comment> data) {
      mContext = context;
      mCommentsList = data;
      mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(ArrayList<Comment> newData) {
      mCommentsList = newData;
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

      Comment comment = (Comment)  getItem(position);
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
    private ArrayList<CandidateInterviewItem> mInterviews;

    InterviewsAdapter(Context context, ArrayList<CandidateInterviewItem> data) {
      mContext = context;
      mInterviews = data;
      mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(ArrayList<CandidateInterviewItem> newData) {
      mInterviews = newData;
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

      CandidateInterviewItem interview = (CandidateInterviewItem)  getItem(position);
      status.setText(interview.getStatus().toString());
      date.setText(interview.getDate().toString());
      List<User> interviewers = interview.getInterviewers();
      StringBuilder users = new StringBuilder();
      for (User interviewer: interviewers){
        String firstName =
            interviewer.getFirstName() != null ? interviewer.getFirstName() : "";
        String lastName =
            interviewer.getLastName() != null ? interviewer.getLastName() : "";
        users.append(firstName + " " + lastName + "\n");
      }
      interviewersText.setText(users);

      return v;
    }
  }
}
