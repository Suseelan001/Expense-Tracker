package com.example.expensetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_all_records_dao")
data class SettingsAllRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val accountName:String,
    val timePeriod: TimePeriod,
    val budgetMode: BudgetMode,
    val carryOver: CarryOver,
    val hideFuture: Boolean=false,
    val dropBox: String="",
    val darkTheme: String="Off",
    val showTransactionNote: Boolean=false,
    val currencySymbol: String="Default",
    val fontStyle: String="Roboto",
    val iconType: String="Filled",
    val tabPosition:String="Bottom",
    val reminder: Reminder,
    val localTips: Boolean=false,
    val passcode: String="",
    )
data class TimePeriod(
    val spending: String="Monthly",
    val monthStart: String="1",
    val weekStartDay: String="Monday"
)
data class BudgetMode(
    val isEnabled: Boolean=false,
    val budgetAmount: Double=0.00,
    val isIncludeIncome: Boolean=false
)
data class CarryOver(
    val isEnabled: Boolean=false,
    val isPositive: Boolean=false,
)
data class Reminder(
    val frequencyType: String="Every Week",
    val time: String="12:00",
)

