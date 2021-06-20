package com.example.favsongs.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.favsongs.db.entity.UserEntity.Companion.TABLE_NAME
import com.example.favsongs.login.model.User

@Entity(tableName = TABLE_NAME)
data class UserEntity (
    @PrimaryKey (autoGenerate = true) private val id: Int,
    @ColumnInfo(name = NAME) private val name: String,
    @ColumnInfo(name = PASSWORD) private val password: String,
    @ColumnInfo(name = PICTURE) private val picture: String
) : User {
    
    companion object {
        const val TABLE_NAME = "user"
        const val NAME = "name"
        const val PASSWORD = "password"
        const val PICTURE = "picture"
    }

    override fun getId(): Int {
        return id
    }

    override fun getName(): String {
        return name
    }

    override fun getPicture(): String {
        return picture
    }

    override fun getPassword(): String {
        return password
    }

}