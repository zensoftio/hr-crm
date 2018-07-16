package com.erkprog.zensofthrcrm.ui.interviews.createInterview;

import android.content.Context;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.InterviewRequest;
import com.erkprog.zensofthrcrm.data.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateInterviewPresenter implements CreateInterviewContract.Presenter {

  private CreateInterviewContract.View mView;
  private Context mContext;
  private ApiInterface mApiService;
  private static final String CONTENT_TYPE = "application/json";

  CreateInterviewPresenter(Context context, ApiInterface service) {
    mContext = context;
    mApiService = service;
  }

  @Override
  public void onSetDateButtonClick() {
    mView.startDatePicker();
  }

  @Override
  public void onCreateButtonClick(int candidateId, List<Integer> interviewersId, String date) {
    mView.showProgress();
    if (interviewersId == null || date == null) {
      mView.showMessage(mContext.getString(R.string.set_up_interviewers));
      mView.dismissProgress();
      return;
    }

    InterviewRequest request = new InterviewRequest(candidateId, interviewersId, date);
    createNewInterview(request);
  }

  private void createNewInterview(InterviewRequest request) {
    mApiService.postInterview(CONTENT_TYPE, request).enqueue(new Callback<Interview>() {
      @Override
      public void onResponse(Call<Interview> call, Response<Interview> response) {
        if (isViewAttached()) {
          mView.dismissProgress();
          if (response.isSuccessful()) {
            mView.showMessage(mContext.getString(R.string.interview_created));
          } else {
            mView.showMessage(mContext.getString(R.string.response_not_successfull));
          }
        }
      }

      @Override
      public void onFailure(Call<Interview> call, Throwable t) {
        if (isViewAttached()) {
          mView.dismissProgress();
          mView.showMessage(t.getMessage());
        }
      }
    });
  }


  @Override
  public void bind(CreateInterviewContract.View view) {
    this.mView = view;
  }

  @Override
  public void unbind() {
    this.mView = null;
  }

  private boolean isViewAttached() {
    return mView != null;
  }
}
