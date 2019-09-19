package com.appnio.todo.db

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class RoomRepository(private val database: TodoDatabase) :
    Repository, CoroutineScope by MainScope() {

    private val listeners: MutableList<RepositoryListener> = arrayListOf()

    override fun addItem(item: Todo) {
        launch {
            database.todoDao().insert(Todo(item.id, item.text))
            notifyListeners()
        }
    }

    override fun findItem(id: Int, callback: DataCallback<Todo?>) {
        launch {
            callback.onData(database.todoDao().findById(id))
        }
    }

    override fun removeItem(id: Int) {
        launch {
            findItem(id, object : DataCallback<Todo?> {
                override fun onData(data: Todo?) {
                    data?.let {
                        removeItem(it)
                    }
                }
            })
        }
    }

    override fun removeItem(item: Todo) {
        launch {
            database.todoDao().delete(Todo(item.id, item.text))
            notifyListeners()
        }
    }

    override fun getAllItems(callback: DataCallback<List<Todo>>) {
        launch {
            callback.onData(database.todoDao().getAll())
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