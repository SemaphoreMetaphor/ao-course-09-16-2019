package com.appnio.todo_v2

import android.app.Application
import com.appnio.todo_v2.db.InMemoryRepository
import com.appnio.todo_v2.db.Repository


class TodoApplication : Application() {

    val repository: Repository = InMemoryRepository()
}