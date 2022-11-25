package com.neltech.databaseexample.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neltech.databaseexample.model.BookModel
import com.neltech.databaseexample.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    var book by mutableStateOf(BookModel(0, "", 0))
        private set

    var openDialog by mutableStateOf(false)

    val books = repo.getBook()
    fun getBook(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        book = repo.getBook(id)
    }
    fun addBook(bookModel: BookModel){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addBook(bookModel)
        }
    }
    fun deleteBook(bookModel: BookModel){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteBook(bookModel)
        }
    }

    fun updateTitle(name: String) {
        book = book.copy(
            name = name
        )
    }
    private  val TAG = "BookViewModel"
    fun updateAuthor(price: String) {
        Log.d(TAG, "updateAuthor: "+price)
        try {
            book = book.copy(
                price = price.toInt()
            )
        }catch (x:Exception){

        }

    }

    fun updateBook(book: BookModel) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateBook(book)
    }

    fun openDialog() {
        openDialog =true
    }
    fun closeDialog() {
        openDialog = false
    }
}