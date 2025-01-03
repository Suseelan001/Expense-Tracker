package com.example.expensetracker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.AddCategory
import com.example.expensetracker.repository.AddAccountDatabaseRepository
import com.example.expensetracker.repository.AddCategoryDatabaseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val addCategoryDatabaseRepository: AddCategoryDatabaseRepository
) : ViewModel() {

    fun insertAccount(addAccount: AddCategory) {
        viewModelScope.launch {
            addCategoryDatabaseRepository.insertAccount(addAccount)
        }
    }

    fun getSingleRecord(id:Int): LiveData<AddCategory> {
        return addCategoryDatabaseRepository.getSingleRecord(id)
    }

    fun updateRecord(addAccount: AddCategory) {
        viewModelScope.launch {
            addCategoryDatabaseRepository.updateRecord(addAccount)
        }
    }




    fun clearUserDB() {
        viewModelScope.launch {
            addCategoryDatabaseRepository.clearUserDB()
        }
    }

    fun getAllRecord(categoryType:String): LiveData<List<AddCategory>> {
        return addCategoryDatabaseRepository.getAllRecord(categoryType)
    }

    fun deleteSingleRecord(id:Int) {
        viewModelScope.launch {
            addCategoryDatabaseRepository.deleteSingleRecord(id)

        }
    }


}
