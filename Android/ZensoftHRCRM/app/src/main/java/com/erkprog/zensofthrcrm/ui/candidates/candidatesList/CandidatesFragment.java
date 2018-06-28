package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;

import java.util.List;

public class CandidatesFragment extends Fragment implements CandidatesContract.View {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_candidates_list, container, false);
        return v;
    }

    @Override
    public void showCandidates(List<Candidate> candidates) {

    }

    @Override
    public void showCandidateDetailUi(int candidateId) {

    }

    @Override
    public void showLoadingCandidatesError() {

    }

    @Override
    public void showNoCandidates() {

    }

    @Override
    public void showToast(String message) {

    }
}
