package com.example.favsongs.network;

import android.os.Build;

import com.example.favsongs.search.RetrieveSongsCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRequestController implements Callback<ItunesResponse>, SongNetworkRequestController {
    private static final String BASE_URL = "https://itunes.apple.com";

    private RetrieveSongsCallback mCallback;

    public void search(String searchValue, RetrieveSongsCallback callback) {
        mCallback = callback;
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ItunesAPI itunesAPI = retrofit.create(ItunesAPI.class);
        Call<ItunesResponse> call = itunesAPI.searchSongs(searchValue);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ItunesResponse> call, Response<ItunesResponse> response) {
        if(response.isSuccessful()) {
            ItunesResponse itunesResponse = response.body();
            mCallback.onSuccess(itunesResponse.getSongs());
        } else {
            mCallback.onError();
        }
    }

    @Override
    public void onFailure(Call<ItunesResponse> call, Throwable t) {
        mCallback.onError();
    }
}
