package com.erkprog.zensofthrcrm.data;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.network.RemoteDataSource;
import com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.CandidateDetailPresenter;

public class DataRepository{

  private static DataRepository instance = null;

  private RemoteDataSource mRemoteDataSource;

  private  DataRepository(RemoteDataSource remoteDataSource) {
    mRemoteDataSource = remoteDataSource;
  }

  public static DataRepository getInstance(Context context) {
    if (instance == null) {
      return new DataRepository(RemoteDataSource.getInstance(context));
    }

    return instance;
  }


  public void getDetailCandidateFromJson(RemoteDataSource.OnDetailCandidateLoadFinishedListener
                                             listener) {
    mRemoteDataSource.getDetailCandidateFromJson(listener);
  }
}
