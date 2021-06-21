package com.example.favsongs.network;

import com.example.favsongs.db.entity.SongEntity;
import com.example.favsongs.search.model.ItunesSong;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItunesAPI {
    @GET("/search?country=US&media=music&attribute=artistTerm&limit=100&")
    Call<ItunesResponse>searchSongs(@Query("term") String searchValue);
}
