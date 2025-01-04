package com.example.expensetracker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.model.AddCategory
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
    fun initializeDefaultCategories() {
        viewModelScope.launch {
            val categories = addCategoryDatabaseRepository.getAllRecordNow()
            if (categories.isEmpty()) {
                val defaultCategories = listOf(
                    AddCategory(category = "Petrol", categoryType = "expense"),
                    AddCategory(category = "Fees", categoryType = "expense"),
                    AddCategory(category = "Hospital", categoryType = "expense"),
                    AddCategory(category = "Food", categoryType = "expense"),
                    AddCategory(category = "Bus", categoryType = "expense"),
                    AddCategory(category = "Salary", categoryType = "income"),
                    AddCategory(category = "FD Return", categoryType = "income"),
                    AddCategory(category = "Interest", categoryType = "income")
                )
                defaultCategories.forEach { insertAccount(it) }
            }
        }
    }

}
