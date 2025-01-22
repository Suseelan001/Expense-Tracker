package com.example.expensetracker.hilt

import android.content.Context
import androidx.room.Room
import com.example.expensetracker.database.AppRoomDataBase
import com.example.expensetracker.database.AccountDAO
import com.example.expensetracker.database.CategoryDAO
import com.example.expensetracker.database.SettingsDataDAO
import com.example.expensetracker.database.TransactionDAO

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModel {

    @Provides
    @Singleton
    fun providerContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppRoomDataBase::class.java, "EXPENSE_TRACKER_DB"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun provideTaskDAO(db: AppRoomDataBase): AccountDAO = db.taskDAO()


    @Provides
    @Singleton
    fun provideAddTransactionDAO(db: AppRoomDataBase): TransactionDAO = db.transactionDAO()


    @Provides
    @Singleton
    fun provideAddCategoryDAO(db: AppRoomDataBase): CategoryDAO = db.categoryDAO()




    @Provides
    @Singleton
    fun provideSettingsDataDAO(db: AppRoomDataBase): SettingsDataDAO = db.settingsDataDAO()



}
