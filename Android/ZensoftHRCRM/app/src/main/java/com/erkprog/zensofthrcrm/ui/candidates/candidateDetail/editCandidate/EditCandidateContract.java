package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.editCandidate;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.ui.BaseView;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

import java.util.List;

public interface EditCandidateContract extends BaseView {

  interface View extends BaseView {

    void showMessage(String t);

    void showProgress();

    void onFinishedRequest(int candidateId);

  }

  interface Presenter extends ILifecycle<View> {

    void updateCadidate(Candidate candidate);

  }

}
