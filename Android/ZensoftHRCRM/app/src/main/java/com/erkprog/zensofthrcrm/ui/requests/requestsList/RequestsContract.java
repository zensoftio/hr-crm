package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import com.erkprog.zensofthrcrm.data.entity.Request;

import java.util.List;

public interface RequestsContract {

    interface View {
      void showRequests(List<Request> requests);

      void showToast(String message);

      boolean isActive();
    }

    interface Presenter {
      void loadData();

      void onRequestItemClick(Request request);
    }

  }

