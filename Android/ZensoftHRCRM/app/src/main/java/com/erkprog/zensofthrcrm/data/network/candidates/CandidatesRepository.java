package com.erkprog.zensofthrcrm.data.network.candidates;

import android.content.Context;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.CandidatesResponse;
import com.erkprog.zensofthrcrm.data.network.test.RestClientTest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidatesRepository {

    private final Context mContext;

    public interface OnLoadFinishedListener {
        void onFinished(Response<CandidatesResponse> response);

        void onFailure(Throwable t);
    }

    public CandidatesRepository(Context context) {
        mContext = context;
    }

    public void getCandidatesFromJson(final OnLoadFinishedListener listener) {
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
}
