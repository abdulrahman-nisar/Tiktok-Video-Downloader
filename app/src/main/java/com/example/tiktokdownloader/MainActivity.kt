package com.example.tiktokdownloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiktokdownloader.ui.TiktokDownloaderViewModel
import com.example.tiktokdownloader.ui.navigation.NavigationRoot
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
                    NavigationRoot(
                        viewModel = viewModel
                    )

            }
        }
    }
}
