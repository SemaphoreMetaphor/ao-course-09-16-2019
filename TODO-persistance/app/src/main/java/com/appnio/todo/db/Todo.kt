package com.appnio.todo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "text") var text: String
)