package com.erkprog.zensofthrcrm.data.network;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.entity.RequestsResponse;
import com.erkprog.zensofthrcrm.data.entity.VacanciesResponse;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ApiInterface {

  @GET("candidates")
  Call<CandidatesResponse> getCandidates();

  @GET("candidates/{id}")
  Call<Candidate> getDetailedCandidate(@Path("id") int id);

  @GET("interviews/{id}")
  Call<Interview> getDetailedInterview(@Path("id") int id);

  @GET("vacancies/{id}")
  Call<Vacancy> getDetailedVacancy(@Path("id") int id);

  @GET("interviews")
  Single<InterviewsResponse> getInterviews();

  @PATCH("candidates/{id}")
  Completable updateCandidates(@Path("id") int id, @Header("Content-Type") String content_type,
                               @Body Candidate candidate);

  @GET("vacancies")
  Call<VacanciesResponse> getVacancies();

  @GET("requests")
  Call<RequestsResponse> getRequests();
}
