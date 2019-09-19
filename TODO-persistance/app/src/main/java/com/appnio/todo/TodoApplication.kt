package com.appnio.todo

import android.app.Application
import androidx.room.Room
import com.appnio.todo.db.Repository
import com.appnio.todo.db.RoomRepository
import com.appnio.todo.db.TodoDatabase


class TodoApplication : Application() {


    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java, "todo-database"
        ).build()
        repository = RoomRepository(database)
    }

}