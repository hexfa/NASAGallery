package com.nasagallery.model.repository

import com.nasagallery.model.local.NASAPhotos
import com.nasagallery.model.remote.ApiService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Single
import javax.inject.Inject

@ActivityRetainedScoped
class PhotoRepository @Inject constructor(private val apiService: ApiService) {
    fun getPhoto(): Single<NASAPhotos> = apiService.getPhotos()
}