package com.example.tiktokdownloader.domain

import com.example.tiktokdownloader.data.repository.DownloadRepository
import javax.inject.Inject

class GetDownloadHistoryUseCase @Inject constructor(
    private val repository: DownloadRepository
) {
    suspend operator fun invoke() = repository.getAllDownloadHistory()
}