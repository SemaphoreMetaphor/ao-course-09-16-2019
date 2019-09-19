package com.appnio.todo_v2.db

import com.appnio.todo_v2.model.Todo


interface Repository {

    fun add(todo: Todo)

    fun find(id: Int): Todo?

    fun findAll(): List<Todo>

    fun delete(id: Int)

    fun delete(todo: Todo)

    fun addListener(listener: RepositoryListener)

    fun removeListener(listener: RepositoryListener)
}