package com.erkprog.zensofthrcrm.data.network.test;

import android.content.Context;

import com.erkprog.zensofthrcrm.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestClientTest {

  private static RestServiceTest mRestService = null;

  public static RestServiceTest getClient(Context context) {
    if (mRestService == null) {
      final OkHttpClient client = new OkHttpClient
          .Builder()
          .build();

      final Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(BuildConfig.BASE_URL)
          .client(client)
          .build();

      mRestService = retrofit.create(RestServiceTest.class);
    }
    return mRestService;
  }
}