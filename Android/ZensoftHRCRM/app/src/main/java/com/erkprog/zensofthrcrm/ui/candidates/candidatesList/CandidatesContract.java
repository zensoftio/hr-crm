package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import com.erkprog.zensofthrcrm.data.entity.Candidate;

import java.util.List;

public interface CandidatesContract {

  interface View {

    void showCandidates(List<Candidate> candidates);

    void showCandidateDetailUi(int candidateId);

    void showLoadingCandidatesError();

    void showNoCandidates();

    void showToast(String message);

    boolean isActive();

  }

  interface Presenter {

    void loadCandidates();

    void openCandidateDetails(Candidate requestedCandidate);

    void onCandidateItemClick(Candidate candidate);

  }
}
