package com.example.favsongs.profile;

public class ProfileContract {
    public interface View {
        void displayUserInformation(String name, String image);
        void displayUserPlaylist();
        void openLoginView();
    }

    public interface Presenter {
        void retrieveUserInformation();
        void retrieveUserPlaylist();
        void updateUserProfile(String name, String picture);
        void selectSongFromPlaylist();
        void logout();
    }
}
