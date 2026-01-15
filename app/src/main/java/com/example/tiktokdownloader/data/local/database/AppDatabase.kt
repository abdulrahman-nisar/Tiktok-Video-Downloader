package com.example.tiktokdownloader.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tiktokdownloader.data.local.dao.DownloadHistoryDao
import com.example.tiktokdownloader.data.local.entity.DownloadHistory

@Database(
    entities = [DownloadHistory::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun downloadHistoryDao(): DownloadHistoryDao
}