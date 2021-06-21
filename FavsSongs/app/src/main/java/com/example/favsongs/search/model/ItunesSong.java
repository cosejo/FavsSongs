package com.example.favsongs.search.model;

import com.google.gson.annotations.SerializedName;

public class ItunesSong {
    @SerializedName("artistName")
    private String artist;

    @SerializedName("trackName")
    private String name;

    @SerializedName("collectionName")
    private String discName;

    public String getName() {
        return name;
    }

    public String getDiscName() {
        return discName;
    }
}
