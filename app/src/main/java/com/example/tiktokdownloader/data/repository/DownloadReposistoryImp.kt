package com.example.tiktokdownloader.data.repository

import com.example.tiktokdownloader.data.local.entity.DownloadHistory
import kotlinx.coroutines.flow.Flow

class DownloadReposistoryImp(

) : DownloadRepository {
    override suspend fun downloadTiktokVideo(): Result<DownloadHistory> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDownloadHistory(): Flow<List<DownloadHistory>> {
        TODO("Not yet implemented")
    }

}