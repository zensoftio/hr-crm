package com.erkprog.zensofthrcrm.ui.interviews.interviewDetail;

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
import com.erkprog.zensofthrcrm.data.network.candidates.CandidatesRepository;
import com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.CandidateDetailContract;
import com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.CandidateDetailPresenter;

import java.util.ArrayList;
import java.util.List;

public class InterviewDetailFragment extends Fragment implements InterviewDetailContract.View {

  @Override
  public void showInterviewDetails(Interview interview) {

  }

  @Override
  public void showLoadingInterviewError() {

  }

  @Override
  public void showToast(String message) {

  }
}
