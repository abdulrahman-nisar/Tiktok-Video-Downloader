package com.example.tiktokdownloader.data.repository

import android.content.Context
import com.example.tiktokdownloader.data.local.dao.DownloadHistoryDao
import com.example.tiktokdownloader.data.local.entity.DownloadHistory
import com.example.tiktokdownloader.data.remote.dto.Result
import com.example.tiktokdownloader.data.remote.api.RetrofitInstance
import com.example.tiktokdownloader.util.manager.PictureManager
import com.example.tiktokdownloader.util.manager.VideoDownloaderManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import javax.inject.Inject

/**
 * Implementation of DownloadRepository interface.
 * Handles downloading TikTok videos and managing download history.
 * @param downloadHistoryDao DAO for accessing download history in the database.
 * @param context Application context for file operations.
 * @author Abdulrahman Nisar
 */
class DownloadReposistoryImp @Inject constructor(
  private val downloadHistoryDao: DownloadHistoryDao,
  @ApplicationContext private val context: Context
) : DownloadRepository {
    override suspend fun downloadTiktokVideo(url: String): Result {
        return withContext(Dispatchers.IO) {
            try {
                val meta = RetrofitInstance.api.getVideoDataByUrl(url)
                val data = meta.data
                    ?: return@withContext Result(false, meta.msg ?: "API returned no data")
                val play = data.play
                    ?: return@withContext Result(false, meta.msg ?: "Missing video url (play)")
                val title = data.title.orEmpty()
                val cover = data.cover.orEmpty()

                // create a safe file name
                val safeName = title.takeIf { it.isNotBlank() } ?: "tiktok_video"
                val videoFileName = safeName.replace(Regex("[^A-Za-z0-9_.-]"), "_") + ".mp4"

                // download video
                val videoResult = VideoDownloaderManager.downloadVideo(play, videoFileName, context)
                if (!videoResult.success) {
                    return@withContext Result(false, videoResult.message ?: "Failed to download video")
                }

                val videoPath = videoResult.message // saved path

                // download cover as bytes (do not save to disk)
                val coverBytes: ByteArray? = if (cover.isNotBlank()) {
                    PictureManager.downloadCover(cover)
                } else null

                // insert history with cover blob
                val history = DownloadHistory(
                    id = 0,
                    tiktokUrl = url,
                    filePath = videoPath ?: "",
                    downloadDate = Timestamp(System.currentTimeMillis()).time,
                    title = title,
                    cover = coverBytes
                )
                downloadHistoryDao.insert(history)

                return@withContext Result(true, "Downloaded successfully")
            } catch (e: Exception) {
                return@withContext Result(false, e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getAllDownloadHistory(): kotlinx.coroutines.flow.Flow<List<DownloadHistory>> {
        return downloadHistoryDao.getAllHistories()
    }

}