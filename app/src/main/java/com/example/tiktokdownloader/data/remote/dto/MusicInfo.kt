package com.example.tiktokdownloader.data.remote.dto

data class MusicInfo(
    val album: String,
    val author: String,
    val cover: String,
    val duration: Int,
    val id: String,
    val original: Boolean,
    val play: String,
    val title: String
)