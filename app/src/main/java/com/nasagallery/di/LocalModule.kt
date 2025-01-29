package com.nasagallery.di

import android.content.Context
import com.nasagallery.data.remote.NasaApiService
import com.nasagallery.data.repository.NasaGalleryRepositoryImpl
import com.nasagallery.domain.repository.NasaGalleryRepository
import com.nasagallery.view.theme.ThemePreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideThemePreferenceManager(
        @ApplicationContext context: Context
    ): ThemePreferenceManager {
        return ThemePreferenceManager(context)
    }


    @Provides
    @Singleton
    fun provideAccountingRepository(
        nasaService: NasaApiService
    ): NasaGalleryRepository {
        return NasaGalleryRepositoryImpl(nasaService)
    }
}
