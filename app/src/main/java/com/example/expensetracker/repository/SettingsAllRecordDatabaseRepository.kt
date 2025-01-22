package com.example.expensetracker.repository




import androidx.lifecycle.LiveData
import com.example.expensetracker.database.SettingsDataDAO
import com.example.expensetracker.model.SettingsAllRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class SettingsAllRecordDatabaseRepository @Inject constructor(
    private val taskDao: SettingsDataDAO
) {

    suspend fun insertAccount(task: SettingsAllRecord) {
        withContext(Dispatchers.IO) {
            taskDao.insertAccount(task)
        }
    }
    fun getSingleRecord(accountName: String): LiveData<SettingsAllRecord> {
        return taskDao.getSingleRecord(accountName)
    }

    suspend fun updateRecord(task: SettingsAllRecord) {
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


    suspend fun updateTimePeriod(id: Int,hideFuture:Boolean) {
        withContext(Dispatchers.IO) {
            taskDao.updateTimePeriod(id,hideFuture)
        }
    }
    suspend fun updateDarkTheme(id: Int,darkTheme:String) {
        withContext(Dispatchers.IO) {
            taskDao.updateDarkTheme(id,darkTheme)
        }
    }
    suspend fun updateSymbol(id: Int,symbol:String) {
        withContext(Dispatchers.IO) {
            taskDao.updateSymbol(id,symbol)
        }
    }
    suspend fun updateFont(id: Int,font:String) {
        withContext(Dispatchers.IO) {
            taskDao.updateFont(id,font)
        }
    }
    suspend fun updateCategoryIcon(id: Int,iconType:String) {
        withContext(Dispatchers.IO) {
            taskDao.updateCategoryIcon(id,iconType)
        }
    }
    suspend fun updateTabPosition(id: Int,tabPosition:String) {
        withContext(Dispatchers.IO) {
            taskDao.updateTabPosition(id,tabPosition)
        }
    }
    suspend fun updateShowTransaction(id: Int,showTransactionNote:Boolean) {
        withContext(Dispatchers.IO) {
            taskDao.updateShowTransaction(id,showTransactionNote)
        }
    }


}
