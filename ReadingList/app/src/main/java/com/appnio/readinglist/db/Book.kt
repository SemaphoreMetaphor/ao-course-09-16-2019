package com.appnio.readinglist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "year") var year: String
) : Serializable