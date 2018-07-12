package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Request;
import com.erkprog.zensofthrcrm.ui.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class RequestsFragment extends Fragment implements RequestsContract.View, ItemClickListener{
  private static final String TAG = "REQUESTS FRAGMENT";

  private RequestsContract.Presenter mPresenter;
  private RequestsAdapter mAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initPresenter();
  }

  private void initPresenter() {
    mPresenter = new RequestsPresenter(CRMApplication.getInstance(requireContext())
        .getServiceTest());
    mPresenter.bind(this);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_requests_list, container, false);
    initRecyclerView(v);
    return v;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPresenter.loadData();
  }

  private void initRecyclerView(View v) {
    final RecyclerView recyclerView = v.findViewById(R.id.recycler_view_all_requests);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);

    List<Request> requests = new ArrayList<>();
    mAdapter = new RequestsAdapter(requests, this);
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
  public void showMessage(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onItemClick(int position) {
    mPresenter.onRequestItemClick(mAdapter.getRequest(position));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mPresenter.unbind();
  }
}
