package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.network.candidates.CandidatesRepository;
import com.erkprog.zensofthrcrm.ui.candidates.candidatesList.CandidatesPresenter;

import java.util.List;

public class InterviewsFragment extends Fragment implements InterviewsContract.View {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_interviews_list, container, false);

        return v;
    }

    @Override
    public void showCandidates(List<Interview> candidates) {

    }

    @Override
    public void showNoCandidates() {

    }
}
