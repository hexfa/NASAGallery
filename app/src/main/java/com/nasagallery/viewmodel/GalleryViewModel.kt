package com.nasagallery.viewmodel

import com.nasagallery.data.model.NASAPhotos
import com.nasagallery.domain.usecase.NasaGalleryUseCase
import com.nasagallery.view.theme.ThemePreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val nasaGalleryUseCase: NasaGalleryUseCase,
    private val preferenceManager: ThemePreferenceManager
) :
    BaseViewModel() {

    private val _photo = MutableStateFlow<NASAPhotos?>(null)
    val photo: StateFlow<NASAPhotos?> get() = _photo

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error


    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> get() = _isDarkTheme


    fun toggleTheme() {
        viewModelScope.launch {
            val newTheme = !_isDarkTheme.value
            preferenceManager.setDarkTheme(newTheme)
        }
    }

    init {
        viewModelScope.launch {
            preferenceManager.isDarkTheme.collect { isDark ->
                _isDarkTheme.value = isDark
            }
        }
    }

    fun fetchPhoto() {
        viewModelScope.launch {
            val result = nasaGalleryUseCase()
            try {
                _photo.value = result
            } catch (e: Exception) {
                _error.emit("An error occurred: ${e.message}")
                e.printStackTrace()
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        // Cancel the ViewModel's scope when the ViewModel is cleared
        viewModelScope.cancel()
    }
}
