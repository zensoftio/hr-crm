package com.erkprog.zensofthrcrm.data.network;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.entity.VacanciesResponse;
import com.erkprog.zensofthrcrm.data.network.test.RestClientTest;
import com.erkprog.zensofthrcrm.data.network.test.RestServiceTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {

  private static RemoteDataSource instance = null;

  private Context mContext;

  // for testing with local JSON files
  private RestServiceTest mTestApi;

  private RemoteDataSource(Context context) {
    mContext = context;
    mTestApi = RestClientTest.getClient(mContext);
  }

  public static RemoteDataSource getInstance(Context context) {
    if (instance == null) {
      return new RemoteDataSource(context);
    }

    return instance;
  }

  /*
  Load all candidates from JSON file
   */

  public interface OnCandidatesLoadFinishedListener {
    void onFinished(Response<CandidatesResponse> response);

    void onFailure(Throwable t);
  }

  public void getCandidatesFromJson(final OnCandidatesLoadFinishedListener listener) {
    mTestApi.getCandidates().enqueue(new Callback<CandidatesResponse>() {
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


  /*
  Get detailed candidate info from JSON file
   */

  public interface OnDetailCandidateLoadFinishedListener {
    void onFinished(Response<Candidate> response);

    void onFailure(Throwable t);
  }

  public void getDetailCandidateFromJson(final OnDetailCandidateLoadFinishedListener listener) {
    mTestApi.getDetailedCandidate().enqueue(new Callback<Candidate>() {
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


  /*
  Get all vacancies from JSON file
   */

  public interface OnVacanciesLoadFinishedListener {
    void onFinished(Response<VacanciesResponse> response);

    void onFailure(Throwable t);
  }

  public void getVacanciesFromJson(final OnVacanciesLoadFinishedListener listener) {
    mTestApi.getVacancies().enqueue(new Callback<VacanciesResponse>() {
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
