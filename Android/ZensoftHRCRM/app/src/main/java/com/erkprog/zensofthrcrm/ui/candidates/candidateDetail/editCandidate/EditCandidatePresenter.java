package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.editCandidate;

import android.util.Log;

import com.erkprog.zensofthrcrm.data.db.SQLiteHelper;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EditCandidatePresenter implements EditCandidateContract.Presenter {

  private EditCandidateContract.View mView;
  private ApiInterface mService;
  private SQLiteHelper mSQLiteHelper;
  private CompositeDisposable disposable = new CompositeDisposable();
  private static final String CONTENT_TYPE = "application/json";


  EditCandidatePresenter(EditCandidateContract.View view, ApiInterface service, SQLiteHelper
      sqliteHelper) {
    mView = view;
    mService = service;
    mSQLiteHelper = sqliteHelper;
  }

  @Override
  public void bind(EditCandidateContract.View view) {
    mView = view;
  }

  @Override
  public void unbind() {
    mView = null;
  }

  private boolean isViewAttached() {
    return mView != null;
  }

  @Override
  public void updateCadidate(final Candidate candidate) {

    candidate.setPosition(null);
    candidate.setComments(null);
    candidate.setCvs(null);
    candidate.setInterviews(null);

    disposable.add(
        mService.updateCandidates(candidate.getId(), CONTENT_TYPE, candidate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableCompletableObserver() {
              @Override
              public void onComplete() {
                if (isViewAttached()) {
                  mSQLiteHelper.saveCandidates(new ArrayList<Candidate>(Arrays.asList(candidate)));
                  mView.onFinishedRequest(candidate.getId());
                }
              }

              @Override
              public void onError(Throwable e) {
                if (isViewAttached()) {
                  mView.showMessage(e.getMessage());
                }
              }
            })
    );
  }
}