package com.erkprog.zensofthrcrm.data.network.interviews;

import android.content.Context;
<<<<<<< HEAD
import android.util.Log;

import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.network.test.RestClientTest;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;
import com.erkprog.zensofthrcrm.ui.interviews.interviewsList.InterviewsContract;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InterviewsRepository implements InterviewsContract.Repository {

    private final Context mContext;

    @Override
    public void getInterviewsList(final OnFinishedListener onFinishedListener, Context mContext) {

        Call<InterviewsResponse> call = RestClientTest.getClient(mContext).getInterviews();


        call.enqueue(new Callback<InterviewsResponse>() {
            @Override
            public void onResponse(Call<InterviewsResponse> call, Response<InterviewsResponse> response) {

                onFinishedListener.onFinished(response.body().getInterviewList());

            }

            @Override
            public void onFailure(Call<InterviewsResponse> call, Throwable t) {

                onFinishedListener.onFailure(t);

            }
        });
    }


    public InterviewsRepository(Context context) {
=======

public class InterviewsRepository {

    private final Context mContext;

    public InterviewsRepository(Context context){
>>>>>>> 57adb4677409b630aade1dd6124c6aff810bf383
        mContext = context;
    }
}
