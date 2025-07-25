package com.tinhtx.domain.usecase

import com.tinhtx.domain.model.MediaItem
import com.tinhtx.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting all media items from the repository.
 * This follows the single responsibility principle, where a use case handles one specific business operation.
 */
class GetMediaUseCase @Inject constructor(
    private val mediaRepository: MediaRepository
) {
    /**
     * Invokes the use case to get a flow of all media items.
     */
    operator fun invoke(): Flow<List<MediaItem>> {
        return mediaRepository.getAllMedia()
    }
}
