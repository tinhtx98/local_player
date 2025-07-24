package com.tinhtx.domain.usecase

import com.tinhtx.domain.model.MediaItem
import com.tinhtx.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMediaUseCase @Inject constructor(
    private val mediaRepository: MediaRepository
) {
    operator fun invoke(): Flow<List<MediaItem>> = mediaRepository.getMedia()

    suspend fun scan() = mediaRepository.scanForMedia()
}
