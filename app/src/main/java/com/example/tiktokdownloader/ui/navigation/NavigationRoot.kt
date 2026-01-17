package com.example.tiktokdownloader.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.tiktokdownloader.ui.TiktokDownloaderViewModel
import com.example.tiktokdownloader.ui.screens.DownloadScreen
import com.example.tiktokdownloader.ui.screens.HistoryScreen
import com.example.tiktokdownloader.ui.screens.MediaPlayerScreen


/**
 * The root composable that sets up navigation for the app.
 * It uses a NavBackStack to manage navigation between different screens.
 * @param viewModel The ViewModel shared across different screens.
 * @author Abdulrahman Nisar
 */
@Composable
fun NavigationRoot(
    viewModel: TiktokDownloaderViewModel
){
    val backStack = rememberNavBackStack(Route.DownloadScreen)

    NavDisplay(
        backStack,
        entryProvider = entryProvider {
            entry<Route.DownloadScreen>{
                DownloadScreen(
                    viewModel = viewModel,
                    onHistoryButtonClick = {
                        backStack.add(Route.HistoryScreen)
                    }
                )
            }
            entry<Route.HistoryScreen>{
                HistoryScreen(
                    viewModel = viewModel,
                    onItemClick = { videoPath ->
                        backStack.add(Route.MediaPlayerScreen(videoPath))
                    },
                    onDownloadButtonClick = {
                        backStack.add(Route.DownloadScreen)
                    }
                )
            }
            entry<Route.MediaPlayerScreen> { args ->
                MediaPlayerScreen(
                    videoPath = args.videoPath
                )
            }
        }
    )
}
