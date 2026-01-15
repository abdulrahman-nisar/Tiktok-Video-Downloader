package com.example.tiktokdownloader.domain

import com.example.tiktokdownloader.data.remote.dto.Result
import com.example.tiktokdownloader.data.repository.DownloadRepository
import com.example.tiktokdownloader.util.UrlValidator
import javax.inject.Inject

class DownloadVideoUseCase @Inject constructor(
    private val repository: DownloadRepository
) {
    suspend operator fun invoke(videoUrl: String): Result {
        return if(UrlValidator.isTikTokUrl(videoUrl)){
            repository.downloadTiktokVideo(videoUrl)
        } else {
            Result(false, "Invalid TikTok URL")
        }
    }
}