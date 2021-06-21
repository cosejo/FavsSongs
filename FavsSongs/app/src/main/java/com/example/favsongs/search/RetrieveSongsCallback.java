package com.example.favsongs.search;

import com.example.favsongs.search.model.ItunesSong;

import java.util.List;

public interface RetrieveSongsCallback {
    void onSuccess(List<ItunesSong> songsList);
    void onError();
}