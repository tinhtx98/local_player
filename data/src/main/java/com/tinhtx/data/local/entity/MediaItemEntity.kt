package com.tinhtx.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_items")
data class MediaItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "artist")
    val artist: String?,

    @ColumnInfo(name = "uri")
    val uri: String,

    @ColumnInfo(name = "artwork_uri")
    val artworkUri: String?,

    @ColumnInfo(name = "album")
    val album: String?,

    @ColumnInfo(name = "duration")
    val duration: Long,

    @ColumnInfo(name = "media_type")
    val mediaType: String // "SONG" or "VIDEO"
)
