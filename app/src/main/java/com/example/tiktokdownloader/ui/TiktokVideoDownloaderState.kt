package com.example.tiktokdownloader.ui

data class TiktokVideoDownloaderState(
    val videoUrl: String = "",
    val isDownloading: Boolean = false,
    val downloadSuccess: Boolean? = null,
    val downloadMessage: String = ""
)
