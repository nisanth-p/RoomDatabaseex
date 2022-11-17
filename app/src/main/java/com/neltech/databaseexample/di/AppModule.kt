package com.neltech.databaseexample.di

import android.content.Context
import androidx.room.Room
import com.neltech.databaseexample.database.BookDao
import com.neltech.databaseexample.database.MyDatabase
import com.neltech.databaseexample.repo.MyRepoImpl
import com.neltech.databaseexample.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, "androidbook").build()
    }

    @Provides
    fun provideDao(myDatabase: MyDatabase): BookDao {
        return myDatabase.bookDAO()
    }

    @Provides
    fun provideRepo(bookDao: BookDao): Repository {
        return MyRepoImpl(bookDao)
    }


}