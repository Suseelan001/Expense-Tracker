package com.example.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expensetracker.model.AddAccount


@Dao
interface AccountDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(task: AddAccount)

    @Update
    suspend fun updateRecord(task: AddAccount)


    @Query("SELECT * FROM add_account_dao")
    fun getAllRecord(): LiveData<List<AddAccount>>

    @Query("DELETE FROM add_account_dao")
    suspend fun clearUserDB()

    @Query("SELECT * FROM add_account_dao WHERE id = :id")
    fun getSingleRecord(id: Int): LiveData<AddAccount>

    @Query("SELECT * FROM add_account_dao WHERE primaryAccount = 1")
    fun getPrimaryAccount(): LiveData<AddAccount>

    @Query("UPDATE add_account_dao SET primaryAccount = :primaryAccountType WHERE id = :id")
    suspend fun updateAccountTypeRecord(id: Int, primaryAccountType: Boolean)

    @Query("DELETE FROM add_account_dao WHERE id = :id")
    suspend fun deleteSingleRecord(id: Int)

}