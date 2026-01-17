package com.example.tiktokdownloader.data.repository

import com.example.tiktokdownloader.data.local.entity.DownloadHistory
import com.example.tiktokdownloader.data.remote.dto.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for downloading TikTok videos and managing download history.
 * @author Abdulrahman Nisar
 */
interface DownloadRepository {
    suspend fun downloadTiktokVideo(url:String): Result
    suspend fun getAllDownloadHistory(): Flow<List<DownloadHistory>>
}