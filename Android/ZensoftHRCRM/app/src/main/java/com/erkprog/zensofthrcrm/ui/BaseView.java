package com.erkprog.zensofthrcrm.ui;

import android.content.Context;

public interface BaseView {

  void showMessage(String message);

  boolean hasInternetConnection(Context context);

}