package com.neltech.databaseexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "androidbook")
data class BookModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    var price:Int
)