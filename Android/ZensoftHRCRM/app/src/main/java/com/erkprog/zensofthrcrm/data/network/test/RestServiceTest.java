package com.erkprog.zensofthrcrm.data.network.test;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.entity.CriteriasResponse;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.InterviewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.erkprog.zensofthrcrm.data.network.test.EndPointsConstants.ALL_CANDIDATES;
import static com.erkprog.zensofthrcrm.data.network.test.EndPointsConstants.ALL_CRITERIAS;
import static com.erkprog.zensofthrcrm.data.network.test.EndPointsConstants.ALL_INTERVIEWS;
import static com.erkprog.zensofthrcrm.data.network.test.EndPointsConstants.CANDIDATE_DETAILS;
import static com.erkprog.zensofthrcrm.data.network.test.EndPointsConstants.INTERVIEW_DETAILS;

public interface RestServiceTest {

  @GET(ALL_CANDIDATES)
  Call<CandidatesResponse> getCandidates();

  @GET(ALL_CRITERIAS)
  Call<CriteriasResponse> getCriteries();

  @GET(CANDIDATE_DETAILS)
  Call<Candidate> getDetailedCandidate();

  @GET(ALL_INTERVIEWS)
  Call<InterviewsResponse> getInterviews();

  @GET(INTERVIEW_DETAILS)
  Call<Interview> getDetailedInterview();


}
