package com.example.qr_test.di

import android.app.Application
import com.example.qr_test.core.helper.AppCache
import com.example.qr_test.core.helper.CacheHelper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAppCacheManager(app: Application): AppCache {
        return CacheHelper(app)
    }
}