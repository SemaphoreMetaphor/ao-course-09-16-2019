package com.appnio.todo.db


interface Repository {

    fun addItem(item: Todo)
    fun findItem(id: Int, callback: DataCallback<Todo?>)
    fun removeItem(id: Int)
    fun removeItem(item: Todo)
    fun getAllItems(dataCallback: DataCallback<List<Todo>>)
    fun addListener(listener: RepositoryListener)
}