package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import android.util.Log;

import com.erkprog.zensofthrcrm.data.DataRepository;
import com.erkprog.zensofthrcrm.data.entity.Request;
import com.erkprog.zensofthrcrm.data.entity.RequestsResponse;
import com.erkprog.zensofthrcrm.data.network.RemoteDataSource;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Response;

public class RequestsPresenter implements RequestsContract.Presenter, RemoteDataSource.OnRequestsLoadFinishedListener {

  private static final String TAG = "REQUESTS PRESENTER";

  private RequestsContract.View mView;
  private DataRepository mRepository;

  public RequestsPresenter (RequestsContract.View view, DataRepository repository) {
    mView = view;
    mRepository = repository;
  }

  @Override
  public void loadData() {
    mRepository.getRequestsFromJson(this);
  }

  @Override
  public void onFinished(Response<RequestsResponse> response) {
    if (mView.isActive() && response.isSuccessful()){
      Log.d(TAG, new GsonBuilder().setPrettyPrinting().create().toJson(response));
      List<Request> requests = response.body().getRequestList();
      if (requests != null) {
        mView.showRequests(requests);
      } else {
        Log.d(TAG, "onFinished: requests list in response is null");
      }
    }
  }

  @Override
  public void onFailure(Throwable t) {

  }


  @Override
  public void onRequestItemClick(Request request) {

  }
}
