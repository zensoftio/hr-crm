package com.erkprog.zensofthrcrm.ui.candidates.candidatesList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.network.candidates.CandidatesRepository;

import java.util.ArrayList;
import java.util.List;

public class CandidatesFragment extends Fragment implements CandidatesContract.View {

    private CandidatesContract.Presenter mPresenter;
    private CandidatesAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_candidates_list, container, false);
        initRecyclerView(v);
        mPresenter = new CandidatesPresenter(this, new CandidatesRepository(getActivity()));
        mPresenter.loadCandidates();

        return v;
    }

    private void initRecyclerView(View v ) {

        final RecyclerView recyclerView = v.findViewById(R.id.recycler_view_all_candidates);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(itemDecoration);

        List<Candidate> candidates = new ArrayList<>();
        mAdapter = new CandidatesAdapter(getActivity(), candidates);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showCandidates(List<Candidate> candidates) {
mAdapter.loadNewData(candidates);
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
