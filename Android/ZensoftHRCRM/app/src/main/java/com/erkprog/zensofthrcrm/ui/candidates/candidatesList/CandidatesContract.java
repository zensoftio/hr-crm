package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.ui.BaseView;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

import java.util.List;

public interface CandidatesContract {

  interface View extends BaseView {

    void showCandidates(List<Candidate> candidates);

    void showCandidateDetailUi(int candidateId);

  }

  interface Presenter extends ILifecycle<View> {

    void loadCandidates();

    void onCandidateItemClick(Candidate candidate);

  }
}
