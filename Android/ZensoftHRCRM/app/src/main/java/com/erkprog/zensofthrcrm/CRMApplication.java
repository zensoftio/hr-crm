package com.erkprog.zensofthrcrm;

import android.app.Application;
import android.content.res.Resources;

public class CRMApplication extends Application {

  public static Resources resources;



  @Override
  public void onCreate() {
    super.onCreate();
    resources = getResources();
  }
}
