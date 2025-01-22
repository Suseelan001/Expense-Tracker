package com.example.expensetracker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.model.SettingsAllRecord
import com.example.expensetracker.repository.SettingsAllRecordDatabaseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SettingsAllRecordViewModel @Inject constructor(
    private val repository: SettingsAllRecordDatabaseRepository
) : ViewModel() {

    fun insertAccount(addAccount: SettingsAllRecord) {
        viewModelScope.launch {
            repository.insertAccount(addAccount)
        }
    }

    fun getSingleRecord(accountName:String): LiveData<SettingsAllRecord> {
        return repository.getSingleRecord(accountName)
    }
    fun updateRecord(addAccount: SettingsAllRecord) {
        viewModelScope.launch {
            repository.updateRecord(addAccount)
        }
    }

    fun clearUserDB() {
        viewModelScope.launch {
            repository.clearUserDB()
        }
    }


    fun deleteSingleRecord(id:Int) {
        viewModelScope.launch {
            repository.deleteSingleRecord(id)

        }
    }


    fun updateTimePeriod(id: Int,hideFuture:Boolean) {
        viewModelScope.launch {
            repository.updateTimePeriod(id,hideFuture)

        }
    }

    fun updateDarkTheme(id: Int,darkTheme:String) {
        viewModelScope.launch {
            repository.updateDarkTheme(id,darkTheme)

        }
    }
    fun updateSymbol(id: Int,symbol:String) {
        viewModelScope.launch {
            repository.updateSymbol(id,symbol)

        }
    }
    fun updateFont(id: Int,font:String) {
        viewModelScope.launch {
            repository.updateFont(id,font)

        }
    }
    fun updateCategoryIcon(id: Int,iconType:String) {
        viewModelScope.launch {
            repository.updateCategoryIcon(id,iconType)

        }
    }
    fun updateTabPosition(id: Int,tabPosition:String) {
        viewModelScope.launch {
            repository.updateTabPosition(id,tabPosition)

        }
    }
    fun updateShowTransaction(id: Int,showTransactionNote:Boolean) {
        viewModelScope.launch {
            repository.updateShowTransaction(id,showTransactionNote)

        }
    }


}
