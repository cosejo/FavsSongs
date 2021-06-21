package com.example.favsongs.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.favsongs.search.model.Song

@Entity(tableName = SongEntity.TABLE_NAME)
data class SongEntity (
    @PrimaryKey(autoGenerate = true) private val id: Int,
    @ColumnInfo(name = NAME) private var name: String,
) : Song {

    companion object {
        const val TABLE_NAME = "song"
        const val NAME = "name"
    }

    override fun getId(): Int {
        return id
    }

    override fun getName(): String {
        return name
    }
}