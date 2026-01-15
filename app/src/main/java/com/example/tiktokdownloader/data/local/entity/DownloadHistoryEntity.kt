package com.example.tiktokdownloader.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "download_history")
data class DownloadHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("tiktok_url")
    val tiktokUrl: String,
    @ColumnInfo("file_path")
    val filePath: String,
    @ColumnInfo("download_date")
    val downloadDate: Timestamp,
    @ColumnInfo("title")
    val title: String?,
    @ColumnInfo(name = "cover_blob", typeAffinity = ColumnInfo.BLOB)
    val cover: ByteArray?
)
