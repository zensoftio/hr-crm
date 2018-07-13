package com.erkprog.zensofthrcrm;

import android.app.Application;
import android.content.Context;

import com.erkprog.zensofthrcrm.data.network.ApiClient;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;

public class CRMApplication extends Application {

  private ApiInterface mApiService;

  @Override
  public void onCreate() {
    super.onCreate();

    mApiService = ApiClient.getClient(this);
  }

  public static CRMApplication getInstance(Context context) {
    return (CRMApplication) context.getApplicationContext();
  }

  public ApiInterface getApiService() {
    return mApiService;
  }
}
