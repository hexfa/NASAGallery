package com.nasagallery.di

import android.content.Context
import com.nasagallery.view.theme.ThemePreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {

    @Provides
    @Singleton
    fun provideThemePreferenceManager(
        @ApplicationContext context: Context
    ): ThemePreferenceManager {
        return ThemePreferenceManager(context)
    }
}
