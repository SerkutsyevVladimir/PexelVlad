package com.example.pexelsvlad.domain.repository

import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.models.PhotoSet

interface PhotoRepository {
    suspend fun getCuratedPhotoSet(): PhotoSet

    suspend fun getCuratedPhotosList(): List<Photo>

    suspend fun getSpecificPhoto(id: String): Photo
}