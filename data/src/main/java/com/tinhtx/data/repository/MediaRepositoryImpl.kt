package com.tinhtx.data.repository

import com.tinhtx.data.local.dao.MediaDao
import com.tinhtx.data.mapper.toDomainModel
import com.tinhtx.domain.model.MediaItem
import com.tinhtx.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepositoryImpl @Inject constructor(
    private val mediaDao: MediaDao
) : MediaRepository {

    override fun getAllMedia(): Flow<List<MediaItem>> {
        return mediaDao.getAllMedia().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getSongs(): Flow<List<MediaItem.Song>> {
        return mediaDao.getSongs().map { entities ->
            entities.map { it.toDomainModel() as MediaItem.Song }
        }
    }

    override fun getVideos(): Flow<List<MediaItem.Video>> {
        return mediaDao.getVideos().map { entities ->
            entities.map { it.toDomainModel() as MediaItem.Video }
        }
    }
}
