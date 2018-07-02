package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Cv;

public interface CandidateDetailContract {

  public interface View {

    void showCandidateDetails(Candidate candidate);

    void showLoadingCandidateError();

    void showToast(String message);

    boolean isActive();

  }

  public interface Presenter {

    void loadCandidateInfo();

    void onInterviewItemClicked(CandidateInterviewItem interviewItem);

    void onCommentItemClicked(Comment commentItem);

    void onCvItemClicked(Cv cvItem);
  }
}