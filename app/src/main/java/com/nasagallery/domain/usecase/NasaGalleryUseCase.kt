package com.nasagallery.domain.usecase

import com.nasagallery.data.model.NASAPhotos
import com.nasagallery.domain.repository.NasaGalleryRepository
import javax.inject.Inject

class NasaGalleryUseCase @Inject constructor(
    private val nasaGalleryRepository: NasaGalleryRepository
) {
    suspend operator fun invoke(): NASAPhotos = nasaGalleryRepository.getAllBalanceBriefly()
}