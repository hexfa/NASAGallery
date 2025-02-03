package com.nasagallery.viewmodel

import com.nasagallery.model.local.NASAPhotos
import com.nasagallery.model.repository.PhotoRepository
import com.nasagallery.view.theme.ThemePreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
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
            photoRepository.getPhoto().collect { nasaPhotoes ->
                try {
                    _photo.value = nasaPhotoes
                } catch (e: retrofit2.HttpException) {
                    if (e.code() == 429) {
                        _error.emit("Too many requests. Please try again later.")
                    } else {
                        _error.emit("An unexpected error occurred: ${e.message}")
                    }
                } catch (e: Exception) {
                    _error.emit("An error occurred: ${e.message}")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Cancel the ViewModel's scope when the ViewModel is cleared
        viewModelScope.cancel()
    }
}
