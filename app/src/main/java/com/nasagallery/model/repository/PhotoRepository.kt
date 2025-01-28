package com.nasagallery.model.repository

import com.nasagallery.model.local.NASAPhotos
import com.nasagallery.model.remote.ApiService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class PhotoRepository @Inject constructor(private val apiService: ApiService) {
    fun getPhoto(): Flow<NASAPhotos> = flow {
        emit(apiService.getPhoto()) // Make the API call and emit the result
    }
}