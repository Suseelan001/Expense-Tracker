package com.example.expensetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "add_account_dao")
data class AddAccount(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val accountName: String,
    val color:String )


