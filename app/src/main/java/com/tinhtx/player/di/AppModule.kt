package com.tinhtx.player.di

import android.content.Context
import androidx.room.Room
import com.tinhtx.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tinhtx-player-db"
        ).build()
    }

    @Provides
    fun provideMediaDao(appDatabase: AppDatabase) = appDatabase.mediaDao()
}

@Module
@InstallIn(SingletonComponent::class)
object MediaModule
