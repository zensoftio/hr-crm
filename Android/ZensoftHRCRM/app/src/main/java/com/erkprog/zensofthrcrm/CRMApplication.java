package com.erkprog.zensofthrcrm;

import android.app.Application;
import android.content.Context;

import com.erkprog.zensofthrcrm.data.network.test.RestClientTest;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;

public class CRMApplication extends Application {

  private RestServiceTest mServiceTest;

  @Override
  public void onCreate() {
    super.onCreate();
    mServiceTest = RestClientTest.getClient(this);
  }

  public static CRMApplication getInstance(Context context) {
    return (CRMApplication) context.getApplicationContext();
  }

  public RestServiceTest getServiceTest() {
    return mServiceTest;
  }
}
