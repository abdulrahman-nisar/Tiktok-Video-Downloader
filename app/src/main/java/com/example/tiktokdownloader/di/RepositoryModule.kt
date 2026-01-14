package com.example.tiktokdownloader.di

import com.example.tiktokdownloader.data.repository.DownloadReposistoryImp
import com.example.tiktokdownloader.data.repository.DownloadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDownloadRepository(
        downloadRepositoryImp: DownloadReposistoryImp
    ): DownloadRepository
}