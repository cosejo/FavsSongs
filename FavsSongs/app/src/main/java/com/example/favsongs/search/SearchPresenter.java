package com.example.favsongs.search;

import com.example.favsongs.network.SongNetworkRequestController;
import com.example.favsongs.search.model.ItunesSong;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;
    private SongNetworkRequestController mRequestController;
    private String mSearchValue;

    public SearchPresenter(SearchContract.View view, SongNetworkRequestController requestController, String searchValue) {
        mView = view;
        mRequestController = requestController;
        mSearchValue = searchValue;
    }

    /**
     * Retrieve the songs related to the searchValue
     */
    @Override
    public void retrieveSongs() {
        mRequestController.search(mSearchValue, new RetrieveSongsCallback() {
            @Override
            public void onSuccess(List<ItunesSong> songsList) {
                mView.displaySongs(songsList);
            }

            @Override
            public void onError() {
                mView.errorSearching();
            }
        });
    }

    /**
     * Add Song to User Playlist
     */
    @Override
    public void addSongToPlaylist() {

    }
}
