package com.example.favsongs.db;

import com.example.favsongs.db.entity.UserEntity;
import com.example.favsongs.security.FavSongsEncryptionManager;

public class DataGenerator {
    public static final UserEntity ADMIN_USER = new UserEntity(1, "admin_user", new FavSongsEncryptionManager().encrypt("qwerty123$"), "");
//    public static final UserEntity ADMIN_USER = new UserEntity(1, "admin_user", "qwerty123$", "");
}
