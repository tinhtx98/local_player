package com.tinhtx.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinhtx.domain.model.MediaItem
import com.tinhtx.domain.usecase.GetMediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMediaUseCase: GetMediaUseCase
) : ViewModel() {

    private val _mediaList = MutableStateFlow<List<MediaItem>>(emptyList())
    val mediaList: StateFlow<List<MediaItem>> = _mediaList

    init {
        viewModelScope.launch {
            getMediaUseCase.scan() // Scan for media on startup
            getMediaUseCase().collect {
                _mediaList.value = it
            }
        }
    }
}
