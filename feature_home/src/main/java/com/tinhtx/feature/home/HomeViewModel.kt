package com.tinhtx.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinhtx.domain.model.MediaItem
import com.tinhtx.domain.usecase.GetMediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMediaUseCase: GetMediaUseCase
    // TODO: Add other use cases as they become available
    // private val searchMediaUseCase: SearchMediaUseCase,
    // private val scanLibraryUseCase: ScanLibraryUseCase,
    // private val getRecommendationsUseCase: GetRecommendationsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    init {
        loadMediaItems()
        setupSearch()
        loadQuickActions()
    }

    private fun loadMediaItems() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getMediaUseCase()
                .catch { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "An unknown error occurred"
                        )
                    }
                }
                .collect { mediaItems ->
                    _uiState.update {
                        it.copy(
                            allMediaItems = mediaItems,
                            mediaItems = filterMediaItems(mediaItems, it.activeFilters),
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    private fun setupSearch() {
        _searchQuery
            .debounce(500)
            .distinctUntilChanged()
            .onEach { query ->
                _uiState.update { it.copy(searchQuery = query) }
                performSearch(query)
            }
            .launchIn(viewModelScope)
    }

    private fun performSearch(query: String) {
        val currentState = _uiState.value
        val filteredItems = if (query.isEmpty()) {
            filterMediaItems(currentState.allMediaItems, currentState.activeFilters)
        } else {
            // Simple fuzzy search implementation
            currentState.allMediaItems.filter { item ->
                item.title.contains(query, ignoreCase = true) ||
                item.artist?.contains(query, ignoreCase = true) == true ||
                (item is MediaItem.Song && item.album?.contains(query, ignoreCase = true) == true)
            }
        }

        _uiState.update { it.copy(mediaItems = filteredItems) }
    }

    private fun loadQuickActions() {
        // Initialize quick actions
        val quickActions = listOf(
            QuickAction.RecentlyPlayed("Recently Played", 0),
            QuickAction.Favorites("Favorites", 0),
            QuickAction.Playlists("Playlists", 0)
        )
        _uiState.update { it.copy(quickActions = quickActions) }
    }

    private fun filterMediaItems(items: List<MediaItem>, filters: Set<MediaFilter>): List<MediaItem> {
        if (filters.isEmpty()) return items

        return items.filter { item ->
            filters.all { filter ->
                when (filter) {
                    is MediaFilter.Type -> when (filter.type) {
                        FilterType.AUDIO -> item is MediaItem.Song
                        FilterType.VIDEO -> item is MediaItem.Video
                        FilterType.ALL -> true
                    }
                    is MediaFilter.Duration -> {
                        val duration = when (item) {
                            is MediaItem.Song -> item.duration
                            is MediaItem.Video -> item.duration
                        }
                        duration >= filter.minDuration && duration <= filter.maxDuration
                    }
                }
            }
        }
    }

    fun onSearchChanged(query: String) {
        _searchQuery.value = query
    }

    fun onFilterSelected(filter: MediaFilter) {
        _uiState.update { currentState ->
            val newFilters = if (currentState.activeFilters.contains(filter)) {
                currentState.activeFilters - filter
            } else {
                currentState.activeFilters + filter
            }

            val filteredItems = filterMediaItems(currentState.allMediaItems, newFilters)

            currentState.copy(
                activeFilters = newFilters,
                mediaItems = filteredItems
            )
        }
    }

    fun onMediaSelected(item: MediaItem) {
        // TODO: Implement navigation to player and start playback
        // This will be implemented when navigation and player modules are available
    }

    fun onQuickActionClick(action: QuickAction) {
        // TODO: Implement quick action handling
        // This will depend on the specific action and available navigation
    }

    fun onScanLibrary() {
        // TODO: Implement scan library functionality
        // For now, just reload media items
        loadMediaItems()
    }

    fun onRefresh() {
        loadMediaItems()
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class HomeUiState(
    val searchQuery: String = "",
    val allMediaItems: List<MediaItem> = emptyList(),
    val mediaItems: List<MediaItem> = emptyList(),
    val quickActions: List<QuickAction> = emptyList(),
    val activeFilters: Set<MediaFilter> = emptySet(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)

sealed class QuickAction(val title: String, val count: Int) {
    data class RecentlyPlayed(val name: String, val itemCount: Int) : QuickAction(name, itemCount)
    data class Favorites(val name: String, val itemCount: Int) : QuickAction(name, itemCount)
    data class Playlists(val name: String, val itemCount: Int) : QuickAction(name, itemCount)
}

sealed class MediaFilter {
    data class Type(val type: FilterType) : MediaFilter()
    data class Duration(val minDuration: Long, val maxDuration: Long, val label: String) : MediaFilter()
}

enum class FilterType {
    ALL, AUDIO, VIDEO
}
