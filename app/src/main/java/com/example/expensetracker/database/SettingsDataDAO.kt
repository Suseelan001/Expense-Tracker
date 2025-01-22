package com.example.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expensetracker.model.SettingsAllRecord


@Dao
interface SettingsDataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(task: SettingsAllRecord)

    @Update
    suspend fun updateRecord(task: SettingsAllRecord)

    @Query("DELETE FROM settings_all_records_dao")
    suspend fun clearUserDB()

    @Query("SELECT * FROM settings_all_records_dao WHERE accountName = :accountName")
    fun getSingleRecord(accountName: String): LiveData<SettingsAllRecord>

    @Query("DELETE FROM settings_all_records_dao WHERE id = :id")
    suspend fun deleteSingleRecord(id: Int)


    @Query("UPDATE settings_all_records_dao SET hideFuture = :hideFuture WHERE id = :recordId")
    suspend fun updateTimePeriod(recordId: Int, hideFuture: Boolean)


    @Query("UPDATE settings_all_records_dao SET darkTheme = :darkTheme WHERE id = :recordId")
    suspend fun updateDarkTheme(recordId: Int, darkTheme: String)

    @Query("UPDATE settings_all_records_dao SET currencySymbol = :symbol WHERE id = :recordId")
    suspend fun updateSymbol(recordId: Int, symbol: String)

    @Query("UPDATE settings_all_records_dao SET fontStyle = :font WHERE id = :recordId")
    suspend fun updateFont(recordId: Int, font: String)

    @Query("UPDATE settings_all_records_dao SET showTransactionNote = :showTransactionNote WHERE id = :recordId")
    suspend fun updateShowTransaction(recordId: Int, showTransactionNote: Boolean)

    @Query("UPDATE settings_all_records_dao SET iconType = :iconType WHERE id = :recordId")
    suspend fun updateCategoryIcon(recordId: Int, iconType: String)

    @Query("UPDATE settings_all_records_dao SET tabPosition = :tabPosition WHERE id = :recordId")
    suspend fun updateTabPosition(recordId: Int, tabPosition: String)




}