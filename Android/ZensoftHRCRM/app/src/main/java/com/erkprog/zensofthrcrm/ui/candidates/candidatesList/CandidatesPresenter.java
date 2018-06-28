package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import com.erkprog.zensofthrcrm.data.entity.Candidate;

public class CandidatesPresenter implements CandidatesContract.Presenter {

    private CandidatesContract.View mView;

    public CandidatesPresenter(CandidatesContract.View view){
        mView = view;
    }

    @Override
    public void loadCandidates() {

    }

    @Override
    public void openCandidateDetails(Candidate requestedCandidate) {

    }
}
