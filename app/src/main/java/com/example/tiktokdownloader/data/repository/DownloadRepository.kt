package com.example.tiktokdownloader.data.repository

import com.example.tiktokdownloader.data.local.entity.DownloadHistory
import kotlinx.coroutines.flow.Flow

interface DownloadRepository {
    suspend fun downloadTiktokVideo(): Result<DownloadHistory>
    suspend fun getAllDownloadHistory(): Flow<List<DownloadHistory>>
}