package com.appnio.readinglist_v2.db

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class RoomRepository(val database: BookDatabase) : Repository, CoroutineScope by MainScope() {

    val listeners: MutableList<(() -> Unit)> = arrayListOf()

    override fun insert(book: Book) {
        launch {
            database.bookDao().insert(book)
            notifyListeners()
        }
    }

    override fun delete(book: Book) {
        launch {
            database.bookDao().delete(book)
            notifyListeners()
        }
    }

    override fun find(id: Int, callback: (Book) -> Unit) {
        launch {
            callback.invoke(database.bookDao().find(id))
        }
    }

    override fun findAll(callback: (List<Book>) -> Unit) {
        launch {
            callback.invoke(database.bookDao().findAll())
        }
    }

    override fun addListener(listener: () -> Unit) {
        listeners.add(listener)
    }

    fun notifyListeners() {
        listeners.forEach { it.invoke() }
    }
}