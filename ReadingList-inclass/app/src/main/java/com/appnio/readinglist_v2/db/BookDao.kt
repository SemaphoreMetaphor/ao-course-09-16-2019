package com.appnio.readinglist_v2.db

import androidx.room.*

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT * FROM book")
    suspend fun findAll(): List<Book>

    @Query("SELECT * FROM book WHERE id=:id")
    suspend fun find(id: Int): Book
}