package com.example.expensetracker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.repository.AddTransactionDatabaseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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


    fun getRecordsByDateRange(account: String, startDate: String,endDte:String): LiveData<List<TransactionModel>> {
        return addTransactionDatabaseRepository.getRecordsByDateRange(account, startDate, endDte)
    }

    fun getRecordsByDateRangeAndCategoryType(account: String, startDate: String,endDte:String,categoryType:String): LiveData<List<TransactionModel>> {
        return addTransactionDatabaseRepository.getRecordsByDateRangeAndCategoryType(account, startDate, endDte,categoryType)
    }

    fun getRecordsByDateRangeAndTransactionType(account: String, startDate: String,endDte:String,transactionType:String): LiveData<List<TransactionModel>> {
        return addTransactionDatabaseRepository.getRecordsByDateRangeAndTransactionType(account, startDate, endDte,transactionType)
    }

    fun deleteSingleRecord(id:Int) {
        viewModelScope.launch {
            addTransactionDatabaseRepository.deleteSingleRecord(id)

        }
    }


}
