package com.appnio.readinglist.db


interface Repository {
    fun insert(book: Book)
    fun insertAll(books: List<Book>)
    fun find(id: Int, callback: (Book?) -> Unit)
    fun getAll(callback: (List<Book>) -> Unit)
    fun delete(id: Int)
    fun delete(book: Book)
    fun addDataChangeListener(listener: DataChangeListener)
    fun removeDataChangeListener(listener: DataChangeListener)
}