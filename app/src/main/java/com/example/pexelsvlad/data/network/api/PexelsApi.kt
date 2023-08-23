package com.example.pexelsvlad.data.network.api

import com.example.pexelsvlad.data.network.models.RestPhoto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header

interface PexelsApi {

    @GET("/v1/curated")
    suspend fun getBestImages(@Header("Authorization") authorizationToken: String): List<RestPhoto>



    companion object {
        private const val BASE_URL = "https://api.pexels.com"

        fun getInstance(): PexelsApi {

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create()
        }
    }

}