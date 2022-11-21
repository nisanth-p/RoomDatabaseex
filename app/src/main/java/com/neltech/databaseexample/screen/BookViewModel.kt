package com.neltech.databaseexample.screen

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
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val books = repo.getBook()
    var openDialog by mutableStateOf(false)
    fun openDialog() {
        openDialog =true
    }
    fun closeDialog() {
        openDialog = false
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
}