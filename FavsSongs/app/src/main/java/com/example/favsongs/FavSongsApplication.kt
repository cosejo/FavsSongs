package com.example.favsongs

import android.app.Application
import com.example.favsongs.db.FavSongsRoomDatabase
import com.example.favsongs.login.model.User

internal class FavSongsApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    companion object {
        lateinit var database: FavSongsRoomDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = FavSongsRoomDatabase.getDatabase(this)
    }

    fun database(): FavSongsRoomDatabase {
        return database
    }
}