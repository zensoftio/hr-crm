package com.erkprog.zensofthrcrm.data.network.remote;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.entity.CriteriasResponse;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.entity.RequestsResponse;
import com.erkprog.zensofthrcrm.data.entity.VacanciesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

  @GET("candidates")
  Call<CandidatesResponse> getCandidates();

  @GET("")
  Call<CriteriasResponse> getCriteries();

  @GET("candidates/{id}")
  Call<Candidate> getDetailedCandidate(@Path("id") int id);

  @GET("")
  Call<InterviewsResponse> getInterviews();

  @GET("")
  Call<VacanciesResponse> getVacancies();

  @GET("")
  Call<RequestsResponse> getRequests();
}
