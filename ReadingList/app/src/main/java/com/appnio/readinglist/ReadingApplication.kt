package com.appnio.readinglist

import android.app.Application
import androidx.room.Room
import com.appnio.readinglist.db.BookDatabase
import com.appnio.readinglist.db.Repository
import com.appnio.readinglist.db.RoomRepository


class ReadingApplication : Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        var database: BookDatabase = Room.databaseBuilder(
            this,
            BookDatabase::class.java,
            "book_database"
        )
            .build()
        repository = RoomRepository(database)
    }
}