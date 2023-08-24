package com.example.pexelsvlad.domain.repository

import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.models.PhotoSet

interface PhotoRepository {
    suspend fun getCuratedPhotos(): PhotoSet

    suspend fun getSpecificPhoto(id: String): Photo
}