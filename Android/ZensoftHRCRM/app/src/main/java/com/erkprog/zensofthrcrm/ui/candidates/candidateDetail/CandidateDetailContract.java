package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.ui.BaseView;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

public interface CandidateDetailContract {

  interface View extends BaseView {

    void showCandidateDetails(Candidate candidate);

    void startCreateInterview(Candidate candidate);

  }

  interface Presenter extends ILifecycle<View> {

    void onCreateInterviewClicked();

    void loadCandidateInfo(int candidateId);

    void onInterviewItemClicked(Interview interviewItem);

    void onCvItemClicked(Cv cvItem);
  }
}