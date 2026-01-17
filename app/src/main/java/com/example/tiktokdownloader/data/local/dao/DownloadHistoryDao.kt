package com.example.tiktokdownloader.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tiktokdownloader.data.local.entity.DownloadHistory
import kotlinx.coroutines.flow.Flow

/**
 * Abstract Data Access Object (DAO) for managing download history in the local database.
 * Provides methods to insert and retrieve download history records.
 * @author Abdulrahman Nisar
 */
@Dao
abstract class DownloadHistoryDao {
    /**
     * Inserts a new download history record into the database.
     * If a record with the same primary key already exists, it will be replaced.
     * @param history The DownloadHistory object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   abstract suspend fun insert(history: DownloadHistory)

   /**
    * Retrieves all download history records from the database, ordered by download date in descending order.
    * @return A Flow emitting a list of DownloadHistory objects.
    */
    @Query("SELECT * FROM download_history ORDER BY download_date DESC")
    abstract fun getAllHistories(): Flow<List<DownloadHistory>>

}