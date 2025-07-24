package com.tinhtx.data.repository

import com.tinhtx.data.local.dao.MediaDao
import com.tinhtx.data.local.mapper.toDomain
import com.tinhtx.data.local.mapper.toEntity
import com.tinhtx.data.local.scanner.MediaStoreScanner
import com.tinhtx.domain.model.MediaItem
import com.tinhtx.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaDao: MediaDao,
    private val mediaStoreScanner: MediaStoreScanner
) : MediaRepository {

    override fun getMedia(): Flow<List<MediaItem>> {
        return mediaDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun scanForMedia() {
        val mediaFromStore = mediaStoreScanner.scanForMedia()
        mediaDao.clearAll()
        mediaDao.insertAll(mediaFromStore.map { it.toEntity() })
    }
}
