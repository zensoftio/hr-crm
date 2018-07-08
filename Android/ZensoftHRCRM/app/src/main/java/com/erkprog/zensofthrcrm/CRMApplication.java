package com.erkprog.zensofthrcrm;

import android.app.Application;

public class CRMApplication extends Application {

  //singleton instance
  private static CRMApplication sInstance = null;


  @Override
  public void onCreate() {
    super.onCreate();
    sInstance = this;
  }

  public static CRMApplication getInstance() {
    return sInstance;
  }
}
