package com.example.tiktokdownloader.domain

import com.example.tiktokdownloader.data.remote.dto.Result
import com.example.tiktokdownloader.data.repository.DownloadRepository
import com.example.tiktokdownloader.util.UrlValidator
import javax.inject.Inject

/**
 * Use case for downloading TikTok videos.
 * Validates the URL before proceeding with the download.
 * @param repository The repository responsible for handling downloads.
 * @author Abdulrahman  Nisar
 */
class DownloadVideoUseCase @Inject constructor(
    private val repository: DownloadRepository
) {
    /**
     * Invokes the use case to download a TikTok video.
     * @param videoUrl The URL of the TikTok video to download.
     * @return A Result object indicating success or failure of the download operation.
     * @author Abdulrahman Nisar
     */
    suspend operator fun invoke(videoUrl: String): Result {
        return if(UrlValidator.isTikTokUrl(videoUrl)){
            repository.downloadTiktokVideo(videoUrl)
        } else {
            Result(false, "Invalid TikTok URL")
        }
    }
}