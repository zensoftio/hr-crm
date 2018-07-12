package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.ui.BaseView;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

import java.util.List;

public interface InterviewsContract {

  interface View extends BaseView {

    void showInterviews(List<Interview> interviews);

    void showMessage(String t);

    void showNoInterviews();

    void showProgress();

    void hideProgress();


  }

  interface Presenter extends ILifecycle<View> {

    void onDestroy();

    // ?? void onRefreshData();

    void getInterviewsInternet();

    void getInterviewsLocal();


  }

}
