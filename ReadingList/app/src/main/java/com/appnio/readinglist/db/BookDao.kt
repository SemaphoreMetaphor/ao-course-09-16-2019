package com.appnio.readinglist.db

import androidx.room.*

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Insert
    suspend fun insertAll(books: List<Book>)

    @Query("SELECT * from book WHERE id LIKE :id LIMIT 1")
    suspend fun find(id: Int): Book

    @Query("SELECT * from book")
    suspend  fun getAll(): List<Book>

    @Delete
    suspend fun delete(book: Book)

}