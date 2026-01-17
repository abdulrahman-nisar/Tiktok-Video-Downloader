package com.example.tiktokdownloader.domain

import com.example.tiktokdownloader.data.repository.DownloadRepository
import javax.inject.Inject

/**
 * Use case for retrieving the download history.
 * This class interacts with the DownloadRepository to fetch all download history records.
 * @author Abdulrahman Nisar
 */
class GetDownloadHistoryUseCase @Inject constructor(
    private val repository: DownloadRepository
) {
    /**
     * Invokes the use case to get all download history.
     * @return A list of download history records.
     * @author Abdulrahman Nisar
     */
    suspend operator fun invoke() = repository.getAllDownloadHistory()
}