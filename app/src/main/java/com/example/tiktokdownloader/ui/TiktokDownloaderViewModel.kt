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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TiktokDownloaderViewModel @Inject constructor(
   private val downloadVideoUseCase: DownloadVideoUseCase,
   private val getDownloadHistoryUseCase: GetDownloadHistoryUseCase
):ViewModel() {
    private val _tiktokVideoDownloaderState = MutableStateFlow(TiktokVideoDownloaderState())
    val tiktokVideoDownloaderState : StateFlow<TiktokVideoDownloaderState> = _tiktokVideoDownloaderState

    private val _downloadHistoryState = MutableStateFlow(emptyList<DownloadHistory>())
    val downloadHistoryState : StateFlow<List<DownloadHistory>> = _downloadHistoryState

    init{
        viewModelScope.launch {
            getDownloadHistoryUseCase().collect{ historyList->
                _downloadHistoryState.value = historyList
            }
         }
        }

    fun downloadVideo(tiktokUrl:String){
        viewModelScope.launch {
            _tiktokVideoDownloaderState.value = TiktokVideoDownloaderState(isDownloading = true)
            try {
                downloadVideoUseCase(tiktokUrl)
                _tiktokVideoDownloaderState.value = TiktokVideoDownloaderState(isDownloading = false, downloadSuccess = true)
            }catch (e:Exception){
                _tiktokVideoDownloaderState.value = TiktokVideoDownloaderState(isDownloading = false, downloadSuccess = false, downloadMessage = e.message.toString() )
            }
        }
    }

    }



