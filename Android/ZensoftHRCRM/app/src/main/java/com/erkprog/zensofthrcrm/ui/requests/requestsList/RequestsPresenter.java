package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import android.support.annotation.NonNull;
import com.erkprog.zensofthrcrm.data.entity.Request;
import com.erkprog.zensofthrcrm.data.entity.RequestsResponse;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestsPresenter implements RequestsContract.Presenter {

  private RestServiceTest mServiceTest;
  private RequestsContract.View mView;

  public RequestsPresenter(RestServiceTest serviceTest) {
    mServiceTest = serviceTest;
  }

  @Override
  public void loadData() {
    mServiceTest.getRequests().enqueue(new Callback<RequestsResponse>() {
      @Override
      public void onResponse(@NonNull Call<RequestsResponse> call, @NonNull Response<RequestsResponse> response) {
        if (isViewAttached() && response.isSuccessful() && response.body() != null) {
            mView.showRequests(response.body().getRequestList());
          } else {
            mView.showMessage("Requests list in response is null");
          }
        }

      @Override
      public void onFailure(@NonNull Call<RequestsResponse> call, @NonNull Throwable t) {
        mView.showMessage(t.getMessage());
      }
    });
  }

  @Override
  public void onRequestItemClick(Request request) {

  }

  private boolean isViewAttached() {
    return mView != null;
  }

  @Override
  public void bind(RequestsContract.View view) {
    this.mView = view;
  }

  @Override
  public void unbind() {
    this.mView = null;
  }
}
