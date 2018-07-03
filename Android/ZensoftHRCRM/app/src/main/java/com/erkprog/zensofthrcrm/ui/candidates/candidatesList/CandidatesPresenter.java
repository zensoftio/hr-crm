package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.network.candidates.CandidatesRepository;

public class CandidatesPresenter implements CandidatesContract.Presenter {

    private CandidatesContract.View mView;
    private CandidatesRepository mRepository;

    public CandidatesPresenter(CandidatesContract.View view, CandidatesRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void loadCandidates() {

    }

    @Override
    public void openCandidateDetails(Candidate requestedCandidate) {

    }
}
