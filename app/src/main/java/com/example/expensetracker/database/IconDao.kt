package com.example.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.IconEntity


@Dao
interface IconDao {
    @Query("SELECT * FROM icon_table WHERE category = :category")
    fun getIconsByCategory(category: String): LiveData<List<IconEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIcons(icons: List<IconEntity>)
}
