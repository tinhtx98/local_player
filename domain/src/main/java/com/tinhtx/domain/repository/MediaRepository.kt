package com.tinhtx.domain.repository

import com.tinhtx.domain.model.MediaItem
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    fun getMedia(): Flow<List<MediaItem>>
    suspend fun scanForMedia()
}
