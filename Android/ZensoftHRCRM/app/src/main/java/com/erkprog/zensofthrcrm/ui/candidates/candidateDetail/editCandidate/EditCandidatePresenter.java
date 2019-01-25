package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.editCandidate;

import com.erkprog.zensofthrcrm.data.db.SQLiteHelper;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EditCandidatePresenter implements EditCandidateContract.Presenter {

  private EditCandidateContract.View mView;
  private ApiInterface mService;
  private SQLiteHelper mSQLiteHelper;

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
  public void fetchData() {

  }

}