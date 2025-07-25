package com.tinhtx.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinhtx.domain.model.MediaItem
import com.tinhtx.domain.usecase.GetMediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMediaUseCase: GetMediaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMediaItems()
    }

    private fun loadMediaItems() {
        viewModelScope.launch {
            getMediaUseCase()
                .catch { exception ->
                    _uiState.value = HomeUiState.Error(exception.message ?: "An unknown error occurred")
                }
                .collect { mediaItems ->
                    _uiState.value = if (mediaItems.isEmpty()) {
                        HomeUiState.Empty
                    } else {
                        HomeUiState.Success(mediaItems)
                    }
                }
        }
    }
}

sealed interface HomeUiState {
    data class Success(val mediaItems: List<MediaItem>) : HomeUiState
    data class Error(val message: String) : HomeUiState
    object Empty : HomeUiState
    object Loading : HomeUiState
}
