package com.erkprog.zensofthrcrm.data.network.test;

import android.content.Context;

import com.tientun.mockresponse.FakeInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestClientTest {

    private static RestServiceTest mRestService = null;

    public static RestServiceTest getClient(Context context) {
        if (mRestService == null) {
            final OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new FakeInterceptor(context))
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://mock.api")
                    .client(client)
                    .build();

            mRestService = retrofit.create(RestServiceTest.class);
        }
        return mRestService;
    }
}