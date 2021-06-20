package com.example.favsongs.profile;

import com.example.favsongs.db.dao.UserDAO;
import com.example.favsongs.db.entity.UserEntity;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View mView;
    private UserDAO mUserDAO;
    private UserEntity mCurrentUser;
    private int mUserId;

    public ProfilePresenter(ProfileContract.View view, UserDAO userDAO, int userId) {
        mView = view;
        mUserDAO = userDAO;
        mUserId = userId;
    }

    /**
     * Retrieves User information from database
     */
    @Override
    public void retrieveUserInformation() {
        WeakReference<ProfilePresenter> weakPresenter = new WeakReference<>(this);
        mUserDAO.getUserById(mUserId, new Continuation<UserEntity>() {
            @NotNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NotNull Object o) {
                if (weakPresenter != null){
                    ProfilePresenter profilePresenter = weakPresenter.get();
                    profilePresenter.mCurrentUser = (UserEntity) o;
                    profilePresenter.mView.displayUserInformation(mCurrentUser.getName(), mCurrentUser.getPicture());
                }
            }
        });
    }

    @Override
    public void retrieveUserPlaylist() {
    }

    /**
     * Store in database the user updated information
     * @param name Username new value
     * @param picture picture path new value
     */
    @Override
    public void updateUserProfile(String name, String picture) {
        mCurrentUser.setName(name);
        mCurrentUser.setPicture(picture);
        mUserDAO.update(mCurrentUser);
    }

    @Override
    public void selectSongFromPlaylist() {
    }

    /**
     * Performs user's logout
     */
    @Override
    public void logout() {
        mUserId = 0;
        mCurrentUser = null;
        mView.openLoginView();
    }
}
