package com.example.expensetracker.typeConverter

import androidx.room.TypeConverter
import com.example.expensetracker.model.BudgetMode
import com.example.expensetracker.model.CarryOver
import com.example.expensetracker.model.Reminder
import com.example.expensetracker.model.TimePeriod
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    // TimePeriod Converters
    @TypeConverter
    fun fromTimePeriod(timePeriod: TimePeriod): String {
        return gson.toJson(timePeriod)
    }

    @TypeConverter
    fun toTimePeriod(data: String): TimePeriod {
        val type = object : TypeToken<TimePeriod>() {}.type
        return gson.fromJson(data, type)
    }

    // BudgetMode Converters
    @TypeConverter
    fun fromBudgetMode(budgetMode: BudgetMode): String {
        return gson.toJson(budgetMode)
    }

    @TypeConverter
    fun toBudgetMode(data: String): BudgetMode {
        val type = object : TypeToken<BudgetMode>() {}.type
        return gson.fromJson(data, type)
    }

    // CarryOver Converters
    @TypeConverter
    fun fromCarryOver(carryOver: CarryOver): String {
        return gson.toJson(carryOver)
    }

    @TypeConverter
    fun toCarryOver(data: String): CarryOver {
        val type = object : TypeToken<CarryOver>() {}.type
        return gson.fromJson(data, type)
    }

    // Reminder Converters
    @TypeConverter
    fun fromReminder(reminder: Reminder): String {
        return gson.toJson(reminder)
    }

    @TypeConverter
    fun toReminder(data: String): Reminder {
        val type = object : TypeToken<Reminder>() {}.type
        return gson.fromJson(data, type)
    }
}
