package com.appnio.todo_v2.db

import com.appnio.todo_v2.model.Todo


class InMemoryRepository : Repository {

    val items: MutableList<Todo> = arrayListOf()
    val listeners: MutableList<RepositoryListener> = arrayListOf()

    override fun add(todo: Todo) {
        todo.id = items.size
        items.add(todo)
        notifyListeners()
    }

    override fun find(id: Int): Todo? {
        return items.find { it.id == id }
    }

    override fun findAll(): List<Todo> {
        return items
    }

    override fun delete(id: Int) {
        find(id)?.let {
            delete(it)
        }
    }

    override fun delete(todo: Todo) {
        items.remove(todo)
        notifyListeners()
    }

    override fun addListener(listener: RepositoryListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: RepositoryListener) {
        listeners.remove(listener)
    }

    fun notifyListeners() {
        listeners.forEach { it.onUpdate() }
    }
}