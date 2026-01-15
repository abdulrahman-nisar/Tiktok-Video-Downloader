package com.example.tiktokdownloader.util.Manager

import okhttp3.OkHttpClient
import okhttp3.Request

object PictureManager {
    private val client = OkHttpClient()

    // Download image bytes and return as ByteArray (do not save to disk)
    fun downloadCover(url: String): ByteArray? {
        if (url.isBlank()) return null
        return try {
            val request = Request.Builder().url(url).get().build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return null
                val body = response.body
                if (body == null) return null
                body.bytes()
            }
        } catch (e: Exception) {
            null
        }
    }
}
