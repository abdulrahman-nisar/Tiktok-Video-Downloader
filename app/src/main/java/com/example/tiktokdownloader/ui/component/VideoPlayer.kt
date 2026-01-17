package com.example.tiktokdownloader.ui.component

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.tiktokdownloader.util.player.ExoPlayerManager


@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(videoPath: String) {
    val context = LocalContext.current

    val player = remember(videoPath) {
        ExoPlayerManager.createPlayer(context, videoPath)
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            PlayerView(it).apply {
                this.player = player
                useController = true
                controllerAutoShow = true
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                setShutterBackgroundColor(Color.BLACK)
                setBackgroundColor(Color.BLACK)
            }
        }
    )

    DisposableEffect(Unit) {
        onDispose {
            ExoPlayerManager.release(player)
        }
    }
}
