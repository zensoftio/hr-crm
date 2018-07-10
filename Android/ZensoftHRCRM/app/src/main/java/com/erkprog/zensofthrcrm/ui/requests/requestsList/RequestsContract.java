package com.erkprog.zensofthrcrm.ui.requests.requestsList;

import com.erkprog.zensofthrcrm.data.entity.Request;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

import java.util.List;

public interface RequestsContract {

    interface View {
      void showRequests(List<Request> requests);

      void showMessage(String message);
      }

    interface Presenter extends ILifecycle<View> {
      void loadData();

      void onRequestItemClick(Request request);
    }

  }

