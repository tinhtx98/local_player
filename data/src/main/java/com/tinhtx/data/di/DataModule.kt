package com.tinhtx.data.di

import com.tinhtx.data.repository.MediaRepositoryImpl
import com.tinhtx.domain.repository.MediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMediaRepository(impl: MediaRepositoryImpl): MediaRepository
}
