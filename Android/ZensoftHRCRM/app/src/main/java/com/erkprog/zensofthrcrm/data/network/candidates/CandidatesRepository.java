package com.erkprog.zensofthrcrm.data.network.candidates;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.entity.VacanciesResponse;
import com.erkprog.zensofthrcrm.data.network.test.RestClientTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidatesRepository {

  private final Context mContext;



  public interface OnCandidatesLoadFinishedListener {
    void onFinished(Response<CandidatesResponse> response);

    void onFailure(Throwable t);
  }

  public interface OnDetailCandidateLoadFinishedListener {
    void onFinished(Response<Candidate> response);

    void onFailure(Throwable t);
  }

  public interface OnVacanciesLoadFinishedListener{
    void onFinished(Response<VacanciesResponse> response);

    void onFailure(Throwable t);
  }

  public CandidatesRepository(Context context) {
    mContext = context;
  }

  public void getCandidatesFromJson(final OnCandidatesLoadFinishedListener listener) {
    RestClientTest.getClient(mContext).getCandidates().enqueue(new Callback<CandidatesResponse>() {
      @Override
      public void onResponse(Call<CandidatesResponse> call, Response<CandidatesResponse> response) {
        listener.onFinished(response);
      }

      @Override
      public void onFailure(Call<CandidatesResponse> call, Throwable t) {
        listener.onFailure(t);
      }
    });
  }

  public void getDetailCandidateFromJson(final OnDetailCandidateLoadFinishedListener listener) {
    RestClientTest.getClient(mContext).getDetailedCandidate().enqueue(new Callback<Candidate>() {
      @Override
      public void onResponse(Call<Candidate> call, Response<Candidate> response) {
        listener.onFinished(response);
      }

      @Override
      public void onFailure(Call<Candidate> call, Throwable t) {
        listener.onFailure(t);
      }
    });
  }

  public void getVacanciesFromJson(final OnVacanciesLoadFinishedListener listener) {
    RestClientTest.getClient(mContext).getVacancies().enqueue(new Callback<VacanciesResponse>() {
      @Override
      public void onResponse(Call<VacanciesResponse> call, Response<VacanciesResponse> response) {
        listener.onFinished(response);
      }

      @Override
      public void onFailure(Call<VacanciesResponse> call, Throwable t) {
        listener.onFailure(t);
      }
    });
  }


}