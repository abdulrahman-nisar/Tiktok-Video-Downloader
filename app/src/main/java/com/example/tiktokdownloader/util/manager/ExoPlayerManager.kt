package com.example.tiktokdownloader.util.player

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import java.io.File
import androidx.core.net.toUri

/**
 * Manager object to handle ExoPlayer creation and release
 * @author Abdulrahman Nisar
 */
object ExoPlayerManager {

    /**
     * Create and prepare ExoPlayer for local or remote video
     *
     * @param context Android context
     * @param path content://, file://, absolute file path, or https:// url
     * @author Abdulrahman Nisar
     */
    fun createPlayer(
        context: Context,
        path: String
    ): ExoPlayer {

        val uri = pathToUri(path)

        return ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(uri)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    /**
     * Release ExoPlayer safely
     * @param player ExoPlayer instance
     * @author Abdulrahman Nisar
     */
    fun release(player: ExoPlayer?) {
        player?.release()
    }

    /**
     * Convert path string into valid Uri
     * @param path content://, file://, absolute file path, or https:// url
     * @return Uri instance
     * @author Abdulrahman Nisar
     */
    private fun pathToUri(path: String): Uri {
        return when {
            path.startsWith("content://") -> path.toUri()
            path.startsWith("file://") -> path.toUri()
            path.startsWith("http://") || path.startsWith("https://") ->
                path.toUri()
            else -> Uri.fromFile(File(path))
        }
    }
}
