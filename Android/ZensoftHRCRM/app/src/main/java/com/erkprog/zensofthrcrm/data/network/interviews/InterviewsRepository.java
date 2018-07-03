package com.erkprog.zensofthrcrm.data.network.interviews;

import android.content.Context;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.network.test.RestClientTest;
import com.erkprog.zensofthrcrm.ui.interviews.interviewsList.InterviewsContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        mContext = context;
    }
}
