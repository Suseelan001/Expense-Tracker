package com.example.expensetracker.viewModel

import androidx.lifecycle.ViewModel
import com.example.expensetracker.localStorage.MySharedPreference
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SharedPreferenceViewModel @Inject constructor(private val mySharedPreference: MySharedPreference) : ViewModel() {



    fun getPrimaryAccountName(): String? {
        return mySharedPreference.getPrimaryAccountName()
    }

    fun setPrimaryAccountName(accountName:String){
        mySharedPreference.setPrimaryAccountName(accountName)
    }



}
