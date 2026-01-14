package com.example.tiktokdownloader.di

import android.content.Context
import androidx.room.Room
import com.example.tiktokdownloader.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tiktok_downloader_db"
        ).build()
    }

    @Provides
    fun provideDownloadHistoryDao(database: AppDatabase) = database.downloadHistoryDao()
}

