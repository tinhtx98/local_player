package com.tinhtx.domain.repository

import com.tinhtx.domain.model.MediaItem
import kotlinx.coroutines.flow.Flow

/**
 * Interface for accessing media data.
 * This is part of the domain layer and has no knowledge of the data source (local or remote).
 */
interface MediaRepository {

    /**
     * Gets a flow of all media items (songs and videos) from the data source.
     *
     * @return A Flow emitting a list of [MediaItem].
     */
    fun getAllMedia(): Flow<List<MediaItem>>

    /**
     * Gets a flow of all songs from the data source.
     *
     * @return A Flow emitting a list of [MediaItem.Song].
     */
    fun getSongs(): Flow<List<MediaItem.Song>>

    /**
     * Gets a flow of all videos from the data source.
     *
     * @return A Flow emitting a list of [MediaItem.Video].
     */
    fun getVideos(): Flow<List<MediaItem.Video>>
}
