package com.neltech.databaseexample.repo

import com.neltech.databaseexample.database.BookDao
import com.neltech.databaseexample.model.BookModel
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias ListBooks = List<BookModel>

interface Repository {
    fun addBook(book: BookModel)
    fun getBook(): Flow<ListBooks>
    fun updateBook(book: BookModel)
    fun deleteBook(book: BookModel)
}

 class MyRepoImpl (private var dao: BookDao) : Repository {
    override fun addBook(book: BookModel) {
        dao.add(book)
    }

    override fun getBook(): Flow<ListBooks> {
        return dao.get()
    }

    override fun updateBook(book: BookModel) {
        dao.update(book)
    }

    override fun deleteBook(book: BookModel) {
     dao.delete(book)
    }

}