package com.tinhtx.domain.model

sealed class MediaItem {
    abstract val id: Long
    abstract val title: String
    abstract val artist: String?
    abstract val uri: String
    abstract val artworkUri: String?

    data class Song(
        override val id: Long,
        override val title: String,
        override val artist: String?,
        override val uri: String,
        override val artworkUri: String?,
        val album: String?,
        val duration: Long
    ) : MediaItem()

    data class Video(
        override val id: Long,
        override val title: String,
        override val artist: String?,
        override val uri: String,
        override val artworkUri: String?,
        val duration: Long
    ) : MediaItem()
}
