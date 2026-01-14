package com.example.tiktokdownloader.data.remote.dto

data class VideoMetaData(
    val code: Int,
    val `data`: Data,
    val msg: String,
    val processed_time: Double
)