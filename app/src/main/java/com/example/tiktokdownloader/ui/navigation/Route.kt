package com.example.tiktokdownloader.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Sealed class representing the different screens in the application.
 * Each screen is defined as a data object or data class implementing the Route interface.
 */
@Serializable
sealed interface Route: NavKey{
    @Serializable
    data object  DownloadScreen: Route, NavKey
    @Serializable
    data object HistoryScreen: Route, NavKey
    @Serializable
    data class MediaPlayerScreen(val videoPath: String): Route, NavKey
}