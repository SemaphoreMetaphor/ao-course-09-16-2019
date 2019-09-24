package com.appnio.todo.db

import kotlinx.coroutines.*


class RoomRepository(private val database: TodoDatabase) :
    Repository, CoroutineScope by MainScope() {

    private val listeners: MutableList<RepositoryListener> = arrayListOf()

    override fun addItem(item: Todo) {
        launch {
            database.todoDao().insert(Todo(item.id, item.text))
            notifyListeners()
        }
    }

    override suspend fun findItem(id: Int): Todo? {
        return withContext(Dispatchers.IO) {
            database.todoDao().findById(id)
        }
    }

    override fun removeItem(id: Int) {
        launch {
            findItem(id)?.let {
                removeItem(it)
            }
        }
    }

    override fun removeItem(item: Todo) {
        launch {
            database.todoDao().delete(Todo(item.id, item.text))
            notifyListeners()
        }
    }

    override suspend fun getAllItems(): List<Todo> {
        return withContext(Dispatchers.IO) {
            database.todoDao().getAll()
        }
    }

    override fun addListener(listener: RepositoryListener) {
        listeners.add(listener)
    }

    private fun notifyListeners() {
        listeners.forEach {
            it.onUpdate()
        }
    }
}