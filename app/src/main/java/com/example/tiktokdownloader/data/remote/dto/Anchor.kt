package com.example.tiktokdownloader.data.remote.dto

data class Anchor(
    val actions: List<Action>,
    val anchor_strong: Any,
    val component_key: String,
    val description: String,
    val extra: String,
    val icon: IconX,
    val id: String,
    val keyword: String,
    val log_extra: String,
    val thumbnail: Thumbnail,
    val type: Int
)