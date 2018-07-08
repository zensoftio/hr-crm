package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.DataRepository;
import com.erkprog.zensofthrcrm.data.entity.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestsFragment extends Fragment implements RequestsContract.View, RequestsAdapter.OnItemClickListener {
  private static final String TAG = "REQUESTS FRAGMENT";

  private RequestsContract.Presenter mPresenter;
  private RequestsAdapter mAdapter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_requests_list, container, false);
    initRecyclerView(v);
    mPresenter = new RequestsPresenter(this, DataRepository.getInstance(getActivity()
        .getApplicationContext()));
    mPresenter.loadData();
    return v;
  }

  private void initRecyclerView(View v) {
    final RecyclerView recyclerView = v.findViewById(R.id.recycler_view_all_requests);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);

    List<Request> requests = new ArrayList<>();
    mAdapter = new RequestsAdapter(getActivity(), requests);
    mAdapter.setOnItemClickListener(this);
    recyclerView.setAdapter(mAdapter);
  }

  public static RequestsFragment newInstance() {
    return new RequestsFragment();
  }

  @Override
  public void showRequests(List<Request> requests) {
    mAdapter.loadNewData(requests);
  }

  @Override
  public void showToast(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public boolean isActive() {
    return isAdded();
  }

  @Override
  public void onItemClick(int position) {
    mPresenter.onRequestItemClick(mAdapter.getRequest(position));
  }
}
