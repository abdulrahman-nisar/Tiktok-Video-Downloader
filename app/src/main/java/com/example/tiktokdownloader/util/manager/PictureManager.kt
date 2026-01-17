package com.example.tiktokdownloader.util.manager

import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * PictureManager is responsible for downloading images from given URLs.
 * It uses OkHttpClient to perform network operations.
 * The downloaded image is returned as a ByteArray.
 * This object does not handle saving images to disk; it only retrieves the image data.
 * @author Abdulrahman Nisar
 */
object PictureManager {
    private val client = OkHttpClient()

    /**
     * Downloads an image from the specified URL.
     * @param url The URL of the image to download.
     * @return A ByteArray containing the image data, or null if the download fails.
     * @author Abdulrahman Nisar
     */
    fun downloadCover(url: String): ByteArray? {
        if (url.isBlank()) return null
        return try {
            val request = Request.Builder().url(url).get().build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return null
                val body = response.body ?: return null
                body.bytes()
            }
        } catch (e: Exception) {
            null
        }
    }
}
