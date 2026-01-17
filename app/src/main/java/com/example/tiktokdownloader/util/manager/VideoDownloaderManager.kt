package com.example.tiktokdownloader.util.manager

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.example.tiktokdownloader.data.remote.dto.Result
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

/**
 * Manager responsible for downloading videos.
 * @author Abdulrahman Nisar
 */
object VideoDownloaderManager {

    private val client = OkHttpClient()

    /**
     * Downloads a video from the given URL and saves it to the device's storage.
     * @param playUrl The URL of the video to download.
     * @param fileName The desired name for the saved video file.
     * @param context The context used to access content resolver.
     * @return A Result object indicating success or failure of the download operation.
     * @author Abdulrahman Nisar
     */
    fun downloadVideo(playUrl: String?, fileName: String, context: Context): Result {
        if (playUrl.isNullOrBlank()) return Result(false, "Empty play url")

        return try {
            val request = Request.Builder().url(playUrl).get().build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return Result(false, "Failed to download video: ${response.code}")
                }

                val body = response.body ?: return Result(false, "Empty response body")

                // Prefer saving to the public Movies collection so the file shows up in Gallery.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val resolver = context.contentResolver
                    val values = ContentValues().apply {
                        put(MediaStore.Video.Media.DISPLAY_NAME, fileName)
                        put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
                        put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_MOVIES + File.separator + "TikTokDownloader")
                        put(MediaStore.Video.Media.IS_PENDING, 1)
                    }

                    val uri = resolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)
                        ?: return Result(false, "Failed to create MediaStore entry")

                    try {
                        resolver.openOutputStream(uri)?.use { out ->
                            body.byteStream().use { input ->
                                input.copyTo(out, bufferSize = 8 * 1024)
                            }
                        } ?: return Result(false, "Failed to open output stream")

                        values.clear()
                        values.put(MediaStore.Video.Media.IS_PENDING, 0)
                        resolver.update(uri, values, null, null)

                        return Result(true, uri.toString())
                    } catch (e: Exception) {
                        // Clean up half-written entry
                        resolver.delete(uri, null, null)
                        return Result(false, e.message ?: "Download error")
                    }
                }
                return Result(false, "Unsupported Android version")
            }
        } catch (e: Exception) {
            Result(false, e.message ?: "Download error")
        }
    }
}
