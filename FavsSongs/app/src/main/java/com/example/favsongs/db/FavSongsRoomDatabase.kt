package com.example.favsongs.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.favsongs.db.dao.UserDAO
import com.example.favsongs.db.entity.UserEntity
import java.util.concurrent.Executors


@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class FavSongsRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FavSongsRoomDatabase? = null

        fun getDatabase(context: Context): FavSongsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavSongsRoomDatabase::class.java,
                    "favsongs_database"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadScheduledExecutor().execute {
                            getDatabase(context).userDao().insert(DataGenerator.ADMIN_USER)
                        }
                    }
                }).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
