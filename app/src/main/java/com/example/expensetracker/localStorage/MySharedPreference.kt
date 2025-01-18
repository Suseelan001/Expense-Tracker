package com.example.expensetracker.localStorage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject

class MySharedPreference @Inject constructor(context: Context) {

    private val sharedPreference: SharedPreferences =
        context.getSharedPreferences("EXPENSE_TRACKER", MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreference.edit()




    fun setPrimaryAccountName(accountName: String) {
        editor.putString("Primary_Account", accountName)
        editor.apply()
    }

    fun getPrimaryAccountName(): String? {
        return sharedPreference.getString("Primary_Account", null)
    }



    fun clearAll() {
        editor.putString("Primary_Account", "")
        editor.commit()
    }



}