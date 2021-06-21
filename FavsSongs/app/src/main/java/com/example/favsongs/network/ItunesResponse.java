package com.example.favsongs.network;

import com.example.favsongs.search.model.ItunesSong;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ItunesResponse {
    @SerializedName("resultCount")
    private int resultCount;

    @SerializedName("results")
    private List<ItunesSong> songs;

    public List<ItunesSong> getSongs() {
        return songs;
    }
}
