package com.tinhtx.feature.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinhtx.domain.usecase.GetMediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getMediaUseCase: GetMediaUseCase
) : ViewModel() {

    // A real view model would have more complex state management
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    fun togglePlayPause() {
        _isPlaying.value = !_isPlaying.value
    }

    init {
        // Example of how you might use the use case
        viewModelScope.launch {
            getMediaUseCase().collect {
                // Update UI state with the list of media items
            }
        }
    }
}
