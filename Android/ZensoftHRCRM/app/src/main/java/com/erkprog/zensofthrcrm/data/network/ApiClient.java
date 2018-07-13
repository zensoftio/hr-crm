package com.erkprog.zensofthrcrm.data.network;

import android.content.Context;

import com.erkprog.zensofthrcrm.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {

  private static ApiInterface mApiService = null;

  public static ApiInterface getClient(Context context) {
    if (mApiService == null) {
      final OkHttpClient client = new OkHttpClient
          .Builder()
          .build();

      final Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(BuildConfig.BASE_URL)
          .client(client)
          .build();

      mApiService = retrofit.create(ApiInterface.class);
    }
    return mApiService;
  }
}