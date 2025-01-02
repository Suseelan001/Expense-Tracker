package com.example.expensetracker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.repository.AddAccountDatabaseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val addAccountDatabaseRepository: AddAccountDatabaseRepository
) : ViewModel() {

    fun insertAccount(addAccount: AddAccount) {
        viewModelScope.launch {
            addAccountDatabaseRepository.insertAccount(addAccount)
        }
    }

    fun getSingleRecord(id:Int): LiveData<AddAccount> {
        return addAccountDatabaseRepository.getSingleRecord(id)
    }

    fun updateRecord(addAccount: AddAccount) {
        viewModelScope.launch {
            addAccountDatabaseRepository.updateRecord(addAccount)
        }
    }




    fun clearUserDB() {
        viewModelScope.launch {
            addAccountDatabaseRepository.clearUserDB()
        }
    }

    fun getAllRecord(): LiveData<List<AddAccount>> {
        return addAccountDatabaseRepository.getAllRecord()
    }

    fun deleteSingleRecord(id:Int) {
        viewModelScope.launch {
            addAccountDatabaseRepository.deleteSingleRecord(id)

        }
    }


}
