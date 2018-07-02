package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Cv;

import java.util.List;

public interface CandidateDetailContract {

  public interface View {

    void showCandidateDetails(Candidate candidate);

    void showLoadingCandidateError();

    void showToast(String message);

  }

  public interface Presenter {

    void loadCandidateInfo();

    void onInterviewItemClicked(CandidateInterviewItem interviewItem);

    void onCommentItemClicked(Comment commentItem);

    void onCvItemClicked(Cv cvItem);
  }
}