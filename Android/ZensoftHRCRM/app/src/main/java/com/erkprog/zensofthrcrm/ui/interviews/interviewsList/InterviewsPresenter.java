package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;

import android.content.Context;
import android.util.Log;

import com.erkprog.zensofthrcrm.data.entity.Interview;

import java.util.List;

public class InterviewsPresenter implements InterviewsContract.Presenter, InterviewsContract.Repository.OnFinishedListener {

    private InterviewsContract.View mView;
    private InterviewsContract.Repository mRepository;
    private Context mContext;

    public InterviewsPresenter(InterviewsContract.View mView,InterviewsContract.Repository mRepository,Context mContext) {
        this.mView = mView;
        this.mRepository = mRepository;
        this.mContext = mContext;
    }

    @Override
    public void getInterviews(Context mContext) {

        mRepository.getInterviewsList(this,mContext);

    }

    @Override
    public void onDestroy() {

        mView = null;

    }

    @Override
    public void onFinished(List<Interview> interviews) {
        if(mView != null){
            mView.showInterviews(interviews);
            mView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mView != null){
            mView.showToast(t);
            mView.hideProgress();
        }
    }
}