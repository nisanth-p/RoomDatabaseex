package com.neltech.databaseexample.database

import androidx.room.*
import com.neltech.databaseexample.model.BookModel
import com.neltech.databaseexample.repo.ListBooks
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert
    fun add(book: BookModel)
    @Update
    fun update(book: BookModel)
    @Delete
    fun delete(book: BookModel)
    @Query("SELECT * FROM androidbook ORDER BY id ASC")
    fun get():Flow<ListBooks>

    @Query("SELECT * FROM androidbook WHERE id = :id")
    fun getBook(id: Int): BookModel
}