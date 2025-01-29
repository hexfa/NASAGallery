package com.nasagallery.viewmodel

import com.nasagallery.model.local.NASAPhotos
import com.nasagallery.model.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NasaItemViewModel @Inject constructor(private val photoRepository: PhotoRepository) :
    BaseViewModel() {



    private val _photo = MutableStateFlow<NASAPhotos?>(null)
    val photo: StateFlow<NASAPhotos?> get() = _photo

    fun fetchPhoto(id: String) {
        viewModelScope.launch {
            photoRepository.getPhoto(id).collect { nasaPhotoes ->
                _photo.value = nasaPhotoes
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Cancel the ViewModel's scope when the ViewModel is cleared
        viewModelScope.cancel()
    }
}
