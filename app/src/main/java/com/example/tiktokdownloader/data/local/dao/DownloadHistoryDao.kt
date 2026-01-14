package com.example.tiktokdownloader.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tiktokdownloader.data.local.entity.DownloadHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface  DownloadHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: DownloadHistory)

    @Query("SELECT * FROM download_history ORDER BY download_date DESC")
    suspend fun getAllHistories(): Flow<List<DownloadHistory>>

}