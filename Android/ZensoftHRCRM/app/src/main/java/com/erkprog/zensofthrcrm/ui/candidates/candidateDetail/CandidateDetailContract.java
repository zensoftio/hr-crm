package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import com.erkprog.zensofthrcrm.data.entity.Candidate;

import java.util.List;

public interface CandidateDetailContract {

    public interface View {

        void showCandidateDetails(Candidate candidate);

        void showLoadingCandidateError();

        void showToast(String message);

    }

    public interface Presenter {

        void loadCandidateInfo();

    }
}