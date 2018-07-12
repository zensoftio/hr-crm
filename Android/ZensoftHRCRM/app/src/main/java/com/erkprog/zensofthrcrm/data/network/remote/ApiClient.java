package com.erkprog.zensofthrcrm.data.network.remote;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {

  private static final String BASE_URL = "http://159.65.153.5/api/v1/";

  private static ApiInterface mApiService = null;

  public static ApiInterface getClient(Context context) {
    if (mApiService == null) {
      final OkHttpClient client = new OkHttpClient
          .Builder()
          .build();

      final Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(BASE_URL)
          .client(client)
          .build();

      mApiService = retrofit.create(ApiInterface.class);
    }
    return mApiService;
  }
}