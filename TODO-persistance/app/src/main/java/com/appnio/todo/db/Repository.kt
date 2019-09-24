package com.appnio.todo.db


interface Repository {

    fun addItem(item: Todo)
    suspend fun findItem(id: Int): Todo?
    fun removeItem(id: Int)
    fun removeItem(item: Todo)
    suspend fun getAllItems(): List<Todo>
    fun addListener(listener: RepositoryListener)
}