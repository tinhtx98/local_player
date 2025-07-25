package com.tinhtx.data.local.mapper

import com.tinhtx.data.local.entity.SongEntity
import com.tinhtx.domain.model.MediaItem

fun MediaItem.toEntity(): SongEntity = when (this) {
    is MediaItem.Song -> SongEntity(
        id = id,
        title = title,
        artist = artist ?: "",
        album = album ?: "",
        duration = duration,
        contentUri = uri,
        albumArtUri = artworkUri,
        isVideo = false
    )
    is MediaItem.Video -> SongEntity(
        id = id,
        title = title,
        artist = artist ?: "",
        album = "",
        duration = duration,
        contentUri = uri,
        albumArtUri = artworkUri,
        isVideo = true
    )
}

fun SongEntity.toDomain(): MediaItem = if (isVideo) {
    MediaItem.Video(
        id = id,
        title = title,
        artist = if (artist.isNotEmpty()) artist else null,
        uri = contentUri,
        artworkUri = albumArtUri,
        duration = duration
    )
} else {
    MediaItem.Song(
        id = id,
        title = title,
        artist = if (artist.isNotEmpty()) artist else null,
        uri = contentUri,
        artworkUri = albumArtUri,
        album = if (album.isNotEmpty()) album else null,
        duration = duration
    )
}
