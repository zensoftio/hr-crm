package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.DataRepository;
import com.erkprog.zensofthrcrm.data.entity.Request;

import java.util.List;

public class RequestsFragment extends Fragment implements RequestsContract.View {
  private static final String TAG = "REQUESTS FRAGMENT";

  private RequestsContract.Presenter mPresenter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_requests_list, container, false);
    initRecyclerView(v);
    mPresenter = new RequestsPresenter(this, DataRepository.getInstance(getActivity()
        .getApplicationContext()));
    return v;
  }

  private void initRecyclerView(View v) {
  }

  @Override
  public void showRequests(List<Request> requests) {

  }

  @Override
  public void showToast(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public boolean isActive() {
    return isAdded();
  }
}
