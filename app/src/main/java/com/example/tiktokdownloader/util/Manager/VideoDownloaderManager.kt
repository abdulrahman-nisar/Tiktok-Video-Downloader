package com.example.tiktokdownloader.util.Manager

import android.content.Context
import android.os.Environment
import com.example.tiktokdownloader.data.remote.dto.Result
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object VideoDownloaderManager {

    private val client = OkHttpClient()

    fun downloadVideo(playUrl: String?, fileName: String, context: Context): Result {
        if (playUrl.isNullOrBlank()) return Result(false, "Empty play url")
        return try {
            val request = Request.Builder().url(playUrl).get().build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return Result(false, "Failed to download video: ${response.code}")
                }

                val dir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES)
                    ?: context.filesDir
                if (!dir.exists()) dir.mkdirs()
                val outFile = File(dir, fileName)

                val body = response.body
                if (body == null) return Result(false, "Empty response body")

                var input: InputStream? = null
                var output: FileOutputStream? = null
                try {
                    input = body.byteStream()
                    output = FileOutputStream(outFile)
                    val buffer = ByteArray(8 * 1024)
                    var read: Int
                    while (input.read(buffer).also { read = it } != -1) {
                        output.write(buffer, 0, read)
                    }
                    output.flush()
                } finally {
                    output?.close()
                    input?.close()
                }

                Result(true, outFile.absolutePath)
            }
        } catch (e: Exception) {
            Result(false, e.message ?: "Download error")
        }
    }
}

