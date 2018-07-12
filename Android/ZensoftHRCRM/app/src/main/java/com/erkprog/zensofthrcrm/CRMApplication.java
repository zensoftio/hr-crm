package com.erkprog.zensofthrcrm;

import android.app.Application;
import android.content.Context;

import com.erkprog.zensofthrcrm.data.db.SQLiteHelper;
import com.erkprog.zensofthrcrm.data.network.test.RestClientTest;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;

public class CRMApplication extends Application {

  private RestServiceTest mServiceTest;
  private SQLiteHelper mSQLiteHelper;

  @Override
  public void onCreate() {
    super.onCreate();
    mServiceTest = RestClientTest.getClient(this);
    mSQLiteHelper = new SQLiteHelper(getApplicationContext());
  }

  public static CRMApplication getInstance(Context context) {
    return (CRMApplication) context.getApplicationContext();
  }

  public RestServiceTest getServiceTest() {
    return mServiceTest;
  }

  public SQLiteHelper getSQLiteHelper() {
    return mSQLiteHelper;
  }

}