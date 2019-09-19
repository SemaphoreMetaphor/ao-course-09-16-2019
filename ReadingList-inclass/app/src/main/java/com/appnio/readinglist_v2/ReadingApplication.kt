package com.appnio.readinglist_v2

import android.app.Application
import androidx.room.Room
import com.appnio.readinglist_v2.db.BookDatabase
import com.appnio.readinglist_v2.db.Repository
import com.appnio.readinglist_v2.db.RoomRepository


class ReadingApplication : Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(
            applicationContext,
            BookDatabase::class.java,
            "book-database"
        ).build()
        repository = RoomRepository(database)
    }
}