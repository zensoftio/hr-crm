package com.erkprog.zensofthrcrm.data.network.remote;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.entity.CriteriasResponse;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;
import com.erkprog.zensofthrcrm.data.entity.RequestsResponse;
import com.erkprog.zensofthrcrm.data.entity.VacanciesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

  @GET("candidates?status=&department=&vacancy=")
  Call<CandidatesResponse> getCandidates();

  @GET("")
  Call<CriteriasResponse> getCriteries();

  @GET("")
  Call<Candidate> getDetailedCandidate();

  @GET("")
  Call<InterviewsResponse> getInterviews();

  @GET("")
  Call<VacanciesResponse> getVacancies();

  @GET("")
  Call<RequestsResponse> getRequests();
}
