package com.tinhtx.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinhtx.data.local.entity.MediaItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mediaItems: List<MediaItemEntity>)

    @Query("SELECT * FROM media_items")
    fun getAllMedia(): Flow<List<MediaItemEntity>>

    @Query("SELECT * FROM media_items WHERE media_type = 'SONG'")
    fun getSongs(): Flow<List<MediaItemEntity>>

    @Query("SELECT * FROM media_items WHERE media_type = 'VIDEO'")
    fun getVideos(): Flow<List<MediaItemEntity>>

    @Query("DELETE FROM media_items")
    suspend fun clearAll()
}
