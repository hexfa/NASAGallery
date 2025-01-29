package com.nasagallery.model.remote

import com.nasagallery.model.local.NASAPhotos
import retrofit2.http.GET

const val BASE_URL = "https://api.nasa.gov/planetary/"

interface ApiService {
    @GET("apod?api_key=DEMO_KEY&start_date=2021-01-01&end_date=2021-03-12")
    suspend fun getPhoto(): NASAPhotos
}