package com.example.expensetracker.repository




import androidx.lifecycle.LiveData
import com.example.expensetracker.database.TransactionDAO
import com.example.expensetracker.model.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class AddTransactionDatabaseRepository @Inject constructor(
    private val taskDao: TransactionDAO
) {

    suspend fun insertAccount(record: TransactionModel) {
        withContext(Dispatchers.IO) {
            taskDao.insertAccount(record)
        }
    }
    fun getSingleRecord(id: Int): LiveData<TransactionModel> {
        return taskDao.getSingleRecord(id)
    }


    suspend fun updateRecord(record: TransactionModel) {
        withContext(Dispatchers.IO) {
            taskDao.updateRecord(record)
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


    fun getAllRecord(): LiveData<List<TransactionModel>> {
        return taskDao.getAllRecord( )
    }


}
