package com.example.expensetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "add_category_dao")
data class AddCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val color:String="" ,
    val categoryType:String)


