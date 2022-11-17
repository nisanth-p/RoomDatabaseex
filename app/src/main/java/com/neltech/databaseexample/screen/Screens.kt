package com.neltech.databaseexample.screen
//6
sealed class Screens(val route:String) {
    object Book: Screens("BOOKS_SCREEN")
    object Update: Screens("UPDATE_BOOK_SCREEN")
}