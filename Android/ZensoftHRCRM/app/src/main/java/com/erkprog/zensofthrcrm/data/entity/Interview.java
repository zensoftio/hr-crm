package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Interview {
    @SerializedName("id")
    private Integer id;
    @SerializedName("date")
    private String date;
    @SerializedName("status")
    private Integer status;
    @SerializedName("candidate")
    private Candidate candidate;
    @SerializedName("request")
    private Request request;
    @SerializedName("interviewEvaluations")
    private List<InterviewEvaluation> interviewEvaluationsList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<InterviewEvaluation> getInterviewEvaluationsList() {
        return interviewEvaluationsList;
    }

    public void setInterviewEvaluationsList(List<InterviewEvaluation> interviewEvaluationsList) {
        this.interviewEvaluationsList = interviewEvaluationsList;
    }
}
