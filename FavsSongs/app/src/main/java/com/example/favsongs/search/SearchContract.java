package com.example.favsongs.search;

import com.example.favsongs.search.model.ItunesSong;

import java.util.List;

public class SearchContract {
    public interface View {
        void displaySongs(List<ItunesSong> songsList);
        void searchMoreSongs();
        void errorSearching();
        void errorOnAddingToPlaylist();
    }

    public interface Presenter {
        void retrieveSongs();
        void addSongToPlaylist();
    }
}
