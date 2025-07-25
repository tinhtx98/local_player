package com.tinhtx.data.mapper

import com.tinhtx.data.local.entity.MediaItemEntity
import com.tinhtx.domain.model.MediaItem

fun MediaItemEntity.toDomainModel(): MediaItem {
    return when (this.mediaType) {
        "SONG" -> MediaItem.Song(
            id = this.id,
            title = this.title,
            artist = this.artist,
            uri = this.uri,
            artworkUri = this.artworkUri,
            album = this.album,
            duration = this.duration
        )
        "VIDEO" -> MediaItem.Video(
            id = this.id,
            title = this.title,
            artist = this.artist,
            uri = this.uri,
            artworkUri = this.artworkUri,
            duration = this.duration
        )
        else -> throw IllegalArgumentException("Unknown media type: ${this.mediaType}")
    }
}
