package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidateInterviewItem;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

public interface CandidateDetailContract {

  interface View {

    void showCandidateDetails(Candidate candidate);

    void showMessage(String message);

    void startCreateInterview(Candidate candidate);

  }

  interface Presenter extends ILifecycle<View> {

    void onCreateInterviewClicked();

    void loadCandidateInfo(int candidateId);

    void onInterviewItemClicked(CandidateInterviewItem interviewItem);

    void onCommentItemClicked(Comment commentItem);

    void onCvItemClicked(Cv cvItem);
  }
}