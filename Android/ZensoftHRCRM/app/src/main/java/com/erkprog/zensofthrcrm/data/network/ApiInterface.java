package com.erkprog.zensofthrcrm.data.network;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.entity.CriteriasResponse;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.entity.RequestsResponse;
import com.erkprog.zensofthrcrm.data.entity.VacanciesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

  @GET("candidates")
  Call<CandidatesResponse> getCandidates();

  @GET("candidates/{id}")
  Call<Candidate> getDetailedCandidate(@Path("id") int id);

  @GET("interviews/{id}")
  Call<Interview> getDetailedInterview(@Path("id") int id);

  @GET("interviews")
  Call<InterviewsResponse> getInterviews();

  @GET("vacancies")
  Call<VacanciesResponse> getVacancies();

  @GET("requests")
  Call<RequestsResponse> getRequests();
}
