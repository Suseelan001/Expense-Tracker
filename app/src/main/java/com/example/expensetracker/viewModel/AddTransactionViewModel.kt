package com.example.expensetracker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.repository.AddAccountDatabaseRepository
import com.example.expensetracker.repository.AddTransactionDatabaseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val addTransactionDatabaseRepository: AddTransactionDatabaseRepository
) : ViewModel() {

    fun insertAccount(addAccount: TransactionModel) {
        viewModelScope.launch {
            addTransactionDatabaseRepository.insertAccount(addAccount)
        }
    }

    fun getSingleRecord(id:Int): LiveData<TransactionModel> {
        return addTransactionDatabaseRepository.getSingleRecord(id)
    }

    fun updateRecord(addAccount: TransactionModel) {
        viewModelScope.launch {
            addTransactionDatabaseRepository.updateRecord(addAccount)
        }
    }




    fun clearUserDB() {
        viewModelScope.launch {
            addTransactionDatabaseRepository.clearUserDB()
        }
    }

    fun getAllRecord(): LiveData<List<TransactionModel>> {
        return addTransactionDatabaseRepository.getAllRecord()
    }

    fun getRecordsByType(account:String): LiveData<List<TransactionModel>> {
        return addTransactionDatabaseRepository.getRecordsByType(account)
    }
    fun getRecordsByTypeAndMonth(account:String,monthYear:String): LiveData<List<TransactionModel>> {
        return addTransactionDatabaseRepository.getRecordsByTypeAndMonth(account,monthYear)
    }

    fun deleteSingleRecord(id:Int) {
        viewModelScope.launch {
            addTransactionDatabaseRepository.deleteSingleRecord(id)

        }
    }


}
