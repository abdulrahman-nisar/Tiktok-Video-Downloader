package com.example.tiktokdownloader.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.Date

@Entity(tableName = "download_history")
data class DownloadHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("tiktok_url")
    val tiktokUrl: String,
    @ColumnInfo("file_path")
    val filePath: String,
    @ColumnInfo("download_date")
    val downloadDate: Timestamp
)
