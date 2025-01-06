package com.example.expensetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "add_transaction_dao")
data class TransactionModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val category:String,
    val amount : String,
    val account:String,
    val type:String,
    val createdAt:String)


