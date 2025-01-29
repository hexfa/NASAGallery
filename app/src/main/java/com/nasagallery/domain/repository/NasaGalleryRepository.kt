package com.nasagallery.domain.repository

import com.nasagallery.data.model.NASAPhotos

interface NasaGalleryRepository {
    suspend fun getAllBalanceBriefly(): NASAPhotos
}