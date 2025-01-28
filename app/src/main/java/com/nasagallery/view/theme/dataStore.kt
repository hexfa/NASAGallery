package com.nasagallery.view.theme

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "theme_preferences")


class ThemePreferenceManager(context: Context) {
    private val dataStore = context.dataStore
    private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")

    val isDarkTheme: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[DARK_THEME_KEY] ?: false // Default is light theme
    }

    suspend fun setDarkTheme(isDark: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = isDark
        }
    }
}
