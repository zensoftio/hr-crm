package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;


<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
=======
>>>>>>> 57adb4677409b630aade1dd6124c6aff810bf383
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.network.interviews.InterviewsRepository;
import com.erkprog.zensofthrcrm.ui.RecyclerItemClickListener;
import com.erkprog.zensofthrcrm.ui.interviews.interview.InterviewActivity;
=======
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.network.candidates.CandidatesRepository;
import com.erkprog.zensofthrcrm.ui.candidates.candidatesList.CandidatesPresenter;
>>>>>>> 57adb4677409b630aade1dd6124c6aff810bf383

import java.util.List;

public class InterviewsFragment extends Fragment implements InterviewsContract.View {

<<<<<<< HEAD
    private InterviewsContract.Presenter mPresenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick() {
            Intent intent = new Intent(getActivity(), InterviewActivity.class);
            startActivity(intent);
        }
    };

=======
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

>>>>>>> 57adb4677409b630aade1dd6124c6aff810bf383
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_interviews_list, container, false);

<<<<<<< HEAD
        mPresenter = new InterviewsPresenter(this, new InterviewsRepository(this.getContext()), v.getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_all_interviews);
        layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mPresenter.getInterviews(v.getContext());
        mContext = v.getContext();

        return v;
    }


    @Override
    public void hideProgress() {
=======
        return v;
    }

    @Override
    public void showCandidates(List<Interview> candidates) {
>>>>>>> 57adb4677409b630aade1dd6124c6aff810bf383

    }

    @Override
<<<<<<< HEAD
    public void showProgress() {

    }


    @Override
    public void showInterviews(List<Interview> interviews) {

        InterviewsAdapter adapter = new InterviewsAdapter(mContext, interviews, recyclerItemClickListener);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showToast() {

    }

    @Override
    public void showToast(Throwable t) {

    }

    @Override
    public void showNoInterviews() {

    }

}
=======
    public void showNoCandidates() {

    }
}
>>>>>>> 57adb4677409b630aade1dd6124c6aff810bf383
