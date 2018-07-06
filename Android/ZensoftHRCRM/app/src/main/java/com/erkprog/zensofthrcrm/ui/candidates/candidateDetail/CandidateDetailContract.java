package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Cv;

public interface CandidateDetailContract {

  interface View {

    void showCandidateDetails(Candidate candidate);

    void showLoadingCandidateError();

    void showToast(String message);

    boolean isActive();

    void startCreateInterview(Candidate candidate);

  }

  interface Presenter {

    void onCreateInterviewClicked();

    void loadCandidateInfo(int candidateId);

    void onInterviewItemClicked(CandidateInterviewItem interviewItem);

    void onCommentItemClicked(Comment commentItem);

    void onCvItemClicked(Cv cvItem);
  }
}