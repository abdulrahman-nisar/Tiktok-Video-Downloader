package com.example.tiktokdownloader.data.remote.dto

/**
 * Data class representing the metadata of a TikTok video.
 * @param code The response code from the API.
 * @param msg The message associated with the response.
 * @param processed_time The time taken to process the request.
 * @param data The actual metadata of the video.
 * @author Abdulrahman Nisar
 */
data class VideoMetaData(
    val code: Int? = null,
    val msg: String? = null,
    val processed_time: Double? = null,
    val data: Data? = null
)

