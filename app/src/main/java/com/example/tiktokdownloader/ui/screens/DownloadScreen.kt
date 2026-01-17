package com.example.tiktokdownloader.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Downloading
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.tiktokdownloader.ui.TiktokDownloaderViewModel
import com.example.tiktokdownloader.ui.components.BottomNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DownloadScreen(
    viewModel: TiktokDownloaderViewModel,
    onHistoryButtonClick : () -> Unit
) {
    val downloadVideoState by viewModel.tiktokVideoDownloaderState.collectAsState()
    var url by rememberSaveable { mutableStateOf("") }

    val items = listOf(
        BottomNavigationItem(
            title = "Download",
            selectedIcon = Icons.Filled.Downloading,
            unselectedIcon = Icons.Outlined.Downloading,
        ),
        BottomNavigationItem(
            title = "History",
            selectedIcon = Icons.Filled.History,
            unselectedIcon = Icons.Outlined.History,
        )
    )
    val selectedItemIndex = 0

    val canDownload = url.isNotBlank() && !downloadVideoState.isDownloading

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Download TikTok Video",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            if(index == 1)
                               onHistoryButtonClick()
                        },
                        label = { Text(text = item.title) },
                        alwaysShowLabel = false,
                        icon = {
                            Box {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Paste TikTok URL",
                        style = MaterialTheme.typography.titleMedium
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = url,
                        onValueChange = { url = it },
                        enabled = !downloadVideoState.isDownloading,
                        singleLine = true,
                        label = { Text("Video URL") },
                        placeholder = { Text("https://www.tiktok.com/@...") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Uri,
                            imeAction = ImeAction.Done
                        )
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        enabled = canDownload,
                        onClick = { viewModel.downloadVideo(url.trim()) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Download,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(0.dp))
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = if (downloadVideoState.isDownloading) "Downloading..." else "Download"
                        )
                    }

                    if (downloadVideoState.isDownloading) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Output",
                        style = MaterialTheme.typography.titleMedium
                    )

                    val message = downloadVideoState.downloadMessage
                    val success = downloadVideoState.downloadSuccess

                    Text(
                        text = when {
                            downloadVideoState.isDownloading -> "Downloading... Please wait."
                            success == true && message.isNotBlank() -> message
                            success == true -> "Download successful."
                            success == false && message.isNotBlank() -> message
                            success == false -> "Download failed."
                            else -> "Enter a URL and tap Download."
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = when (success) {
                            true -> MaterialTheme.colorScheme.primary
                            false -> MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }
        }
    }
}