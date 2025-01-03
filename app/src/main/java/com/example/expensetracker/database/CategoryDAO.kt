package com.example.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.AddCategory


@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(task: AddCategory)

    @Update
    suspend fun updateRecord(task: AddCategory)


    @Query("SELECT * FROM add_category_dao WHERE categoryType=:categoryType")
    fun getAllRecord(categoryType:String): LiveData<List<AddCategory>>

    @Query("DELETE FROM add_category_dao")
    suspend fun clearUserDB()

    @Query("SELECT * FROM add_category_dao WHERE id = :id")
    fun getSingleRecord(id: Int): LiveData<AddCategory>

    @Query("DELETE FROM add_category_dao WHERE id = :id")
    suspend fun deleteSingleRecord(id: Int)

}