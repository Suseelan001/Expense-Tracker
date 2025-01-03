package com.example.expensetracker.repository




import androidx.lifecycle.LiveData
import com.example.expensetracker.database.AccountDAO
import com.example.expensetracker.database.CategoryDAO
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.AddCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class AddCategoryDatabaseRepository @Inject constructor(
    private val taskDao: CategoryDAO
) {

    suspend fun insertAccount(task: AddCategory) {
        withContext(Dispatchers.IO) {
            taskDao.insertAccount(task)
        }
    }
    fun getSingleRecord(id: Int): LiveData<AddCategory> {
        return taskDao.getSingleRecord(id)
    }


    suspend fun updateRecord(task: AddCategory) {
        withContext(Dispatchers.IO) {
            taskDao.updateRecord(task)
        }
    }


    suspend fun clearUserDB() {
        withContext(Dispatchers.IO) {
            taskDao.clearUserDB()
        }
    }


    suspend fun deleteSingleRecord(id: Int) {
        withContext(Dispatchers.IO) {
            taskDao.deleteSingleRecord(id)
        }
    }


    fun getAllRecord(categoryType:String): LiveData<List<AddCategory>> {
        return taskDao.getAllRecord(categoryType)
    }


}
