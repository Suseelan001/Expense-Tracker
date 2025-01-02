package com.example.expensetracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.TransactionModel


@Database(entities = [AddAccount::class, TransactionModel::class],   exportSchema = false,version = 3)
abstract class AppRoomDataBase: RoomDatabase() {
    abstract fun taskDAO(): AccountDAO
    abstract fun transactionDAO(): TransactionDAO

}