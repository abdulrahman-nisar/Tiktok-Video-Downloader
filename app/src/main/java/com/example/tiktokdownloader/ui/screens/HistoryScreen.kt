package com.example.tiktokdownloader.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Downloading
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.tiktokdownloader.ui.TiktokDownloaderViewModel
import com.example.tiktokdownloader.ui.components.BottomNavigationItem
import com.example.tiktokdownloader.ui.components.HistoryItem
import com.example.tiktokdownloader.util.byteArrayToBitmap


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: TiktokDownloaderViewModel,
    onItemClick: (String) -> Unit,
    onDownloadButtonClick : () -> Unit,
) {
    val historyState by viewModel.downloadHistoryState.collectAsState()
    var selectedVideoPath by remember { mutableStateOf<String?>(null) }
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
    val selectedItemIndex = 1

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Tiktok Download History",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                })
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            if(index == 0)
                                onDownloadButtonClick()
                        },
                        label = {
                            Text(text = item.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            Box{
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            if (selectedVideoPath != null) {
                item(span = { GridItemSpan(2) }) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        onItemClick(selectedVideoPath!!)
                    }
                }
            }

            items(historyState, key = { it.id }) { item ->
                val picture = item.cover?.let { byteArrayToBitmap(it) }
                HistoryItem(
                    bitmap = picture,
                    videoPath = item.filePath,
                    title = item.title,
                    onClick = { path -> selectedVideoPath = path }
                )
            }
        }
    }


}