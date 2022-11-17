package com.neltech.databaseexample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neltech.databaseexample.model.BookModel

@Database(entities =[BookModel::class] , version = 1, exportSchema = false)
abstract class MyDatabase:RoomDatabase() {
    abstract fun bookDAO():BookDao
}