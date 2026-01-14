package com.example.tiktokdownloader.data.repository

import com.example.tiktokdownloader.data.local.dao.DownloadHistoryDao
import com.example.tiktokdownloader.data.local.entity.DownloadHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DownloadReposistoryImp @Inject constructor(
    downloadHistoryDao: DownloadHistoryDao
) : DownloadRepository {
    override suspend fun downloadTiktokVideo(): Result<DownloadHistory> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDownloadHistory(): Flow<List<DownloadHistory>> {
        TODO("Not yet implemented")
    }

}