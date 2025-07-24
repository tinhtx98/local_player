package com.tinhtx.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tinhtx.data.local.dao.MediaDao
import com.tinhtx.data.local.entity.SongEntity

@Database(entities = [SongEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mediaDao(): MediaDao
}
