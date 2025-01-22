package com.example.expensetracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.AddCategory
import com.example.expensetracker.model.SettingsAllRecord
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.typeConverter.Converters

@TypeConverters(Converters::class)
@Database(entities = [AddAccount::class, TransactionModel::class, AddCategory::class, SettingsAllRecord::class],   exportSchema = false,version = 1)
abstract class AppRoomDataBase: RoomDatabase() {
    abstract fun taskDAO(): AccountDAO
    abstract fun transactionDAO(): TransactionDAO
    abstract fun categoryDAO(): CategoryDAO
    abstract fun settingsDataDAO(): SettingsDataDAO

}