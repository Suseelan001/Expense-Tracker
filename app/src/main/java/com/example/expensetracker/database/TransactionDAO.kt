package com.example.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expensetracker.model.TransactionModel


@Dao
interface TransactionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(record: TransactionModel)

    @Update
    suspend fun updateRecord(record: TransactionModel)


    @Query("SELECT * FROM add_transaction_dao")
    fun getAllRecord(): LiveData<List<TransactionModel>>

    @Query("SELECT * FROM add_transaction_dao  WHERE account = :account")
    fun getRecordsbyType(account:String): LiveData<List<TransactionModel>>

    @Query("DELETE FROM add_transaction_dao")
    suspend fun clearUserDB()

    @Query("SELECT * FROM add_transaction_dao WHERE id = :id")
    fun getSingleRecord(id: Int): LiveData<TransactionModel>

    @Query("DELETE FROM add_transaction_dao WHERE id = :id")
    suspend fun deleteSingleRecord(id: Int)

}