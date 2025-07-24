package com.tinhtx.data.local.mapper

import com.tinhtx.data.local.entity.SongEntity
import com.tinhtx.domain.model.MediaItem

fun MediaItem.toEntity(): SongEntity {
    return SongEntity(
        id = id,
        title = title,
        artist = artist,
        album = album,
        duration = duration,
        contentUri = contentUri,
        albumArtUri = albumArtUri,
        isVideo = isVideo
    )
}

fun SongEntity.toDomain(): MediaItem {
    return MediaItem(
        id = id,
        title = title,
        artist = artist,
        album = album,
        duration = duration,
        contentUri = contentUri,
        albumArtUri = albumArtUri,
        isVideo = isVideo
    )
}
