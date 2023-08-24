package com.example.pexelsvlad.data.network.api

import com.example.pexelsvlad.data.network.models.RestCollectionsSet
import com.example.pexelsvlad.data.network.models.RestPhoto
import com.example.pexelsvlad.data.network.models.RestPhotoSet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsApi {

    @GET("/v1/curated")
    suspend fun getCuratedPhotos(
        @Header("Authorization") authorizationToken: String = AUTHORIZATION_TOKEN,
        @Query("page") page: Int = PAGE_NUMBER,
        @Query("per_page") perPage: Int = PAGE_SIZE_PHOTOS
    ): RestPhotoSet

    @GET("/v1/photos/:id")
    suspend fun getSpecificPhoto(
        @Header("Authorization") authorizationToken: String = AUTHORIZATION_TOKEN,
        @Path("id") id: String
    ): RestPhoto

    @GET("/v1/collections/featured")
    suspend fun getFeaturedCollections(
        @Header("Authorization") authorizationToken: String = AUTHORIZATION_TOKEN,
        @Query("page") page: Int = PAGE_NUMBER,
        @Query("per_page") perPage: Int = PAGE_SIZE_COLLECTIONS
    ): RestCollectionsSet


    companion object {
        private const val AUTHORIZATION_TOKEN =
            "HmqpAI5ZAz05WcanOwGApZvgzQGQdj4HzDL3qHfMXE8wFhdAD5FtermX"

        private const val PAGE_NUMBER = 1

        private const val PAGE_SIZE_PHOTOS = 30

        private const val PAGE_SIZE_COLLECTIONS = 7

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