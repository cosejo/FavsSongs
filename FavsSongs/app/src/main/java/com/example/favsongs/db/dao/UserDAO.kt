package com.example.favsongs.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.favsongs.db.entity.UserEntity

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE name = :name")
    suspend fun getUserByName(name: String): UserEntity

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): UserEntity

    @Insert
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)
}