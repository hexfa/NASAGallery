package com.nasagallery.data.remote

import com.nasagallery.data.model.NASAPhotos
import retrofit2.http.GET

interface NasaApiService {
    @GET("apod?api_key=DEMO_KEY&start_date=2021-01-01&end_date=2021-03-12")
    suspend fun getPhoto(): NASAPhotos
}