package com.appnio.readinglist_v2.db


interface Repository {

    fun insert(book: Book)
    fun delete(book: Book)
    fun find(id: Int, callback: (Book) -> Unit)
    fun findAll(callback: (List<Book>) -> Unit)
    fun addListener(listener: () -> Unit)
}