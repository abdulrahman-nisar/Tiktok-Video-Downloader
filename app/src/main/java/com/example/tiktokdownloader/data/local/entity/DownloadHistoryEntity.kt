package com.example.tiktokdownloader.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a download history entry in the local database.
 * Each entry contains information about a downloaded TikTok video.
 * @author Abdulrahman Nisar
 */
@Entity(tableName = "download_history")
data class DownloadHistory(

    // Unique identifier for each download history entry
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    // URL of the downloaded TikTok video
    @ColumnInfo("tiktok_url")
    val tiktokUrl: String,
    // Local file path where the video is stored
    @ColumnInfo("file_path")
    val filePath: String,
    // Timestamp of when the video was downloaded
    @ColumnInfo("download_date")
    val downloadDate: Long,
    // Title of the TikTok video
    @ColumnInfo("title")
    val title: String?,
    // Cover image of the TikTok video stored as a byte array (BLOB)
    @ColumnInfo(name = "cover_blob", typeAffinity = ColumnInfo.BLOB)
    val cover: ByteArray?
)
