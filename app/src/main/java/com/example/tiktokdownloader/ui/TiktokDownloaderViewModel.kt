package com.example.tiktokdownloader.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiktokdownloader.data.local.entity.DownloadHistory
import com.example.tiktokdownloader.domain.DownloadVideoUseCase
import com.example.tiktokdownloader.domain.GetDownloadHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for TikTok video downloading
 * Handles video download and download history retrieval
 * Uses Hilt for dependency injection
 * Manages UI state with StateFlow
 * @HiltViewModel annotation for Hilt integration
 * @Inject constructor for use case dependencies
 * @property downloadVideoUseCase Use case for downloading videos
 * @property getDownloadHistoryUseCase Use case for retrieving download history
 * @param tiktokVideoDownloaderState StateFlow for TikTok video downloader UI state
 * @param downloadHistoryState StateFlow for download history list
 * @author Abdulrahman Nisar
 */
@HiltViewModel
class TiktokDownloaderViewModel @Inject constructor(
   private val downloadVideoUseCase: DownloadVideoUseCase,
   private val getDownloadHistoryUseCase: GetDownloadHistoryUseCase
):ViewModel() {
    private val _tiktokVideoDownloaderState = MutableStateFlow(TiktokVideoDownloaderState())
    val tiktokVideoDownloaderState = _tiktokVideoDownloaderState.asStateFlow()

    private val _downloadHistoryState = MutableStateFlow(emptyList<DownloadHistory>())
    val downloadHistoryState = _downloadHistoryState.asStateFlow()

    init{
        viewModelScope.launch {
            getDownloadHistoryUseCase().collect{ historyList->
                _downloadHistoryState.value = historyList
            }
         }
    }

    /**
     * Downloads a TikTok video from the provided URL
     * Updates the UI state based on download progress and result
     * @param tiktokUrl The URL of the TikTok video to download
     * @author Abdulrahman Nisar
     */
    fun downloadVideo(tiktokUrl:String){
        viewModelScope.launch {
            _tiktokVideoDownloaderState.value = TiktokVideoDownloaderState(isDownloading = true)
            try {
                val result = downloadVideoUseCase(tiktokUrl)
                if(result.success){
                    _tiktokVideoDownloaderState.value = TiktokVideoDownloaderState(isDownloading = false, downloadSuccess = true, downloadMessage = "Download Successful")
                }
                else {
                    _tiktokVideoDownloaderState.value = TiktokVideoDownloaderState(
                        isDownloading = false,
                        downloadSuccess = false,
                        downloadMessage = result.message ?: "Download Failed"
                    )
                }
            }catch (e:Exception){
                _tiktokVideoDownloaderState.value = TiktokVideoDownloaderState(isDownloading = false, downloadSuccess = false, downloadMessage = e.message.toString() )
            }
        }
    }
    }



