package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import com.erkprog.zensofthrcrm.data.DataRepository;
import com.erkprog.zensofthrcrm.data.entity.Request;

public class RequestsPresenter implements RequestsContract.Presenter {

  private static final String TAG = "REQUESTS PRESENTER";

  private RequestsContract.View mView;
  private DataRepository mRepository;

  public RequestsPresenter (RequestsContract.View view, DataRepository repository) {
    mView = view;
    mRepository = repository;
  }

  @Override
  public void loadData() {

  }

  @Override
  public void onRequestItemClick(Request request) {

  }
}
