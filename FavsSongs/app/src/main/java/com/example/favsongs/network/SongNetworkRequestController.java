package com.example.favsongs.network;

import com.example.favsongs.search.RetrieveSongsCallback;

public interface SongNetworkRequestController {
    void search(String searchValue, RetrieveSongsCallback callback);
}
