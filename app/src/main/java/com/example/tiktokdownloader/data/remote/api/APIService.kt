package com.example.tiktokdownloader.data.remote.api

import com.example.tiktokdownloader.data.remote.dto.VideoMetaData
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getVideoDataByUrl(@Url url: String): VideoMetaData
}