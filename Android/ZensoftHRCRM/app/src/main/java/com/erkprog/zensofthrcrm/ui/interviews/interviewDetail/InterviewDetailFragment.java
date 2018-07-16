package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.Evaluation;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.Interviewer;
import com.erkprog.zensofthrcrm.data.entity.Position;
import com.erkprog.zensofthrcrm.data.entity.SubTitle;
import com.erkprog.zensofthrcrm.data.entity.Title;

import java.util.ArrayList;
import java.util.List;

public class InterviewDetailFragment extends Fragment implements InterviewDetailContract.View {

  private static final String EXTRA_INTERVIEW_ID = "interview_id";

  private InterviewDetailContract.Presenter mPresenter;

  private RecyclerView mRecyclerView;

  private TextView mInitial;
  private TextView mDepartment;
  private TextView mDate;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = new InterviewDetailPresenter(this, CRMApplication.getInstance(requireContext())
        .getApiService(), CRMApplication.getInstance(requireContext()).getSQLiteHelper());
    mPresenter.bind(this);
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_interview_detail, container, false);

    initUI(v);

    mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_all_interviewers);

    if (hasInternetConnection(v.getContext())) {
      mPresenter.getInterviewInternet(getArguments().getInt(EXTRA_INTERVIEW_ID));
    } else {
      mPresenter.getInterviewLocal(getArguments().getInt(EXTRA_INTERVIEW_ID));
    }

    return v;
  }

  private void initUI(View v) {

    mDepartment = v.findViewById(R.id.i_department);
    mInitial = v.findViewById(R.id.i_initial);
    mDate = v.findViewById(R.id.i_date);

  }

  @Override
  public void showInterviewDetails(Interview interview) {

    if (interview.getCandidate() != null) {
      Candidate candidate = interview.getCandidate();

      String candidateName = String.format("%1s %2s", interview.getCandidate().getFirstName(),
          interview
              .getCandidate().getLastName());
      mInitial.setText(candidateName);

      if (candidate.getPosition() != null) {
        Position position = candidate.getPosition();
        if (position.getDepartment() != null) {
          mDepartment.setText(interview.getCandidate().getPosition().getDepartment().getName());
        }
      }
    }

    mDate.setText(interview.getDate());

    if (interview.getInterviewersList() != null) {
      List<Title> list = getList(interview.getInterviewersList());
      InterviewersAdapter adapter = new InterviewersAdapter(list);
      mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      mRecyclerView.setAdapter(adapter);
    }


  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mPresenter.unbind();
  }

  private List<Title> getList(List<Interviewer> interviewers) {
    List<Title> list = new ArrayList<>();
    for (int i = 0; i < interviewers.size(); i++) {
      List<SubTitle> subTitles = new ArrayList<>();
      SubTitle subTitle;
      if (interviewers.get(i).getEvaluaionList() != null) {
        for (int j = 0; j < interviewers.get(i).getEvaluaionList().size(); j++) {
          Evaluation evaluation = interviewers.get(i).getEvaluaionList().get(j);
          subTitle = new SubTitle(evaluation.getCriteria().getName(), evaluation.getRate());
          if (j == 0)
            subTitle.setComment(interviewers.get(i).getComment());
          subTitles.add(subTitle);
        }
      }
      Title model = new Title(interviewers.get(i).getUser().getEmail(),
          subTitles);
      list.add(model);
    }
    return list;
  }


  @Override
  public void showNoInterviewDetails() {

  }

  @Override
  public void showLoadingInterviewError() {

  }

  @Override
  public void showMessage(String message) {

  }

  @Override
  public boolean hasInternetConnection(Context context) {
    return false;
  }

  @Override
  public void showProgress() {

  }

  @Override
  public void dismissProgress() {

  }
}

