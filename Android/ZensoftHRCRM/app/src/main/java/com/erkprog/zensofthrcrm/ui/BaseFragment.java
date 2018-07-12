package com.erkprog.zensofthrcrm.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseFragment extends Fragment {

  /**
   * This method changes action bar title.
   * Set null if you don't want any changes.
   */

  protected abstract String setTitle();

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    String title = setTitle();

    if (title != null) {
      if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
      }
    }

  }
}