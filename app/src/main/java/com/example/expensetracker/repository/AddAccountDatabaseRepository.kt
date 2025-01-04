package com.example.expensetracker.repository




import androidx.lifecycle.LiveData
import com.example.expensetracker.database.AccountDAO
import com.example.expensetracker.model.AddAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class AddAccountDatabaseRepository @Inject constructor(
    private val taskDao: AccountDAO
) {

    suspend fun insertAccount(task: AddAccount) {
        withContext(Dispatchers.IO) {
            taskDao.insertAccount(task)
        }
    }
    fun getSingleRecord(id: Int): LiveData<AddAccount> {
        return taskDao.getSingleRecord(id)
    }

    fun getPrimaryAccount(): LiveData<AddAccount> {
        return taskDao.getPrimaryAccount()
    }



    suspend fun updateAccountTypeRecord(id: Int, primaryAccount:Boolean) {
        withContext(Dispatchers.IO) {
            taskDao.updateAccountTypeRecord(id,primaryAccount)
        }
    }

    suspend fun updateRecord(task: AddAccount) {
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


    fun getAllRecord(): LiveData<List<AddAccount>> {
        return taskDao.getAllRecord( )
    }


}
