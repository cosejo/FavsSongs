package com.example.favsongs.db;

import com.example.favsongs.db.entity.UserEntity;
import com.example.favsongs.security.FakeEncryptionManager;
import com.example.favsongs.security.FavSongsEncryptionManager;

/**
 * Data for populate the Database the fist time
 */
public class DataGenerator {
//    public static final UserEntity ADMIN_USER = new UserEntity(1, "admin_user", new FavSongsEncryptionManager().encrypt("qwerty123$"), "");
    public static final UserEntity ADMIN_USER = new UserEntity(1, "admin_user", new FakeEncryptionManager().encrypt("qwerty123$"), "");
}
