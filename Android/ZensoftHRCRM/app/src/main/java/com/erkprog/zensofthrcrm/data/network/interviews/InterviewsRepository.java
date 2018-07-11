package com.erkprog.zensofthrcrm.data.network.interviews;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.network.test.RestClientTest;
import com.erkprog.zensofthrcrm.ui.interviews.interviewDetail.InterviewDetailContract;
import com.erkprog.zensofthrcrm.ui.interviews.interviewsList.InterviewsContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterviewsRepository implements InterviewsContract.Repository, InterviewDetailContract.Repository {

  @Override
  public void getInterviewsList(final InterviewsContract.Repository.OnFinishedListener onFinishedListener, Context mContext) {

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

  @Override
  public void getInterviewDetails(final InterviewDetailContract.Repository.OnFinishedListener
                                      onFinishedListener, Context mContext) {

    Call<Interview> call = RestClientTest.getClient(mContext).getDetailedInterview();

    call.enqueue(new Callback<Interview>() {
      @Override
      public void onResponse(Call<Interview> call, Response<Interview> response) {
        Interview body = response.body();
        if (body != null && body.getId() != null && body.getCandidate() != null
            && body.getStatus() != null && body.getDate() != null)
          onFinishedListener.onFinished(response.body());
        else {
          // response is null
        }
      }

      @Override
      public void onFailure(Call<Interview> call, Throwable t) {
        onFinishedListener.onFailure(t);
      }
    });
  }


}
