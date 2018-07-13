package com.erkprog.zensofthrcrm;

import android.app.Application;
import android.content.Context;

import com.erkprog.zensofthrcrm.data.db.SQLiteHelper;
import com.erkprog.zensofthrcrm.data.network.ApiClient;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;

public class CRMApplication extends Application {

  private ApiInterface mApiService;
  private SQLiteHelper mSQLiteHelper;

  @Override
  public void onCreate() {
    super.onCreate();
    mSQLiteHelper = new SQLiteHelper(getApplicationContext());
    mApiService = ApiClient.getClient(this);
  }

  public static CRMApplication getInstance(Context context) {
    return (CRMApplication) context.getApplicationContext();
  }

  public ApiInterface getApiService() {
    return mApiService;
  }

  public SQLiteHelper getSQLiteHelper() {
    return mSQLiteHelper;
  }

}