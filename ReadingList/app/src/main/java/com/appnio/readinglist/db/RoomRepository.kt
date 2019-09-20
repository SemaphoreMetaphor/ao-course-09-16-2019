package com.appnio.readinglist.db

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class RoomRepository(private val database: BookDatabase) : Repository,
    CoroutineScope by MainScope() {

    private var listeners: MutableList<DataChangeListener> = arrayListOf()

    override fun insert(book: Book) {
        launch {
            database.bookDao().insert(book)
            notifyListeners()
        }
    }

    override fun insertAll(books: List<Book>) {
        launch {
            database.bookDao().insertAll(books)
            notifyListeners()
        }
    }

    override fun find(id: Int, callback: (Book?) -> Unit) {
        launch {
            callback.invoke(database.bookDao().find(id))
        }
    }

    override fun getAll(callback: (List<Book>) -> Unit) {
        launch {
            callback.invoke(database.bookDao().getAll())
        }
    }

    override fun delete(id: Int) {
        launch {
            find(id) {
                it?.let {
                    delete(it)
                }
            }
        }
    }

    override fun delete(book: Book) {
        launch {
            database.bookDao().delete(book)
            notifyListeners()
        }
    }

    override fun addDataChangeListener(listener: DataChangeListener) {
        listeners.add(listener)
    }

    override fun removeDataChangeListener(listener: DataChangeListener) {
        listeners.remove(listener)
    }

    fun notifyListeners() {
        listeners.forEach { it.onDataUpdated() }
    }
}