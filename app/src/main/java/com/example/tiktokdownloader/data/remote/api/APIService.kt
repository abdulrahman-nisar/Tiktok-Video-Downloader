package com.example.tiktokdownloader.data.remote.api

import com.example.tiktokdownloader.data.remote.dto.VideoMetaData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * APIService defines the Retrofit interface for making network requests
 * to the TikTok video metadata API.
 * @author Abdulrahman Nisar
 */
interface APIService {
    @GET("api/")
    suspend fun getVideoDataByUrl(@Query("url") url: String): VideoMetaData
}