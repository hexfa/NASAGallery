package com.nasagallery.data.repository

import com.nasagallery.data.model.NASAPhotos
import com.nasagallery.data.remote.NasaApiService
import com.nasagallery.domain.repository.NasaGalleryRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NasaGalleryRepositoryImpl @Inject constructor(private val apiService: NasaApiService) :
    NasaGalleryRepository {
    override suspend fun getAllBalanceBriefly(): NASAPhotos = apiService.getPhoto()
}