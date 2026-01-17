package com.example.tiktokdownloader

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiktokdownloader.ui.TiktokDownloaderViewModel
import com.example.tiktokdownloader.ui.navigation.NavigationRoot
import com.example.tiktokdownloader.ui.screens.DownloadScreen
import com.example.tiktokdownloader.ui.screens.HistoryScreen
import com.example.tiktokdownloader.ui.theme.TiktokdownloaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TiktokdownloaderTheme {
                val viewModel: TiktokDownloaderViewModel = viewModel()

                val permission = remember {
                    if (Build.VERSION.SDK_INT >= 33) {
                        Manifest.permission.READ_MEDIA_VIDEO
                    } else {
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    }
                }

                var hasPermission by remember { mutableStateOf(false) }

                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission()
                ) { granted ->
                    hasPermission = granted
                }

                LaunchedEffect(permission) {
                    permissionLauncher.launch(permission)
                }

                if (!hasPermission) {
                    Text("Permission required to read videos and play them.")
                    return@TiktokdownloaderTheme
                }
                NavigationRoot(
                    viewModel = viewModel
                )

            }
        }
    }
}
