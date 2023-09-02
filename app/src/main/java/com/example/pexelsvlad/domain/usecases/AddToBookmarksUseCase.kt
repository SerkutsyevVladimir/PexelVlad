package com.example.pexelsvlad.domain.usecases

import com.example.pexelsvlad.data.repository.db.DBPhotoRepository
import com.example.pexelsvlad.di.qualifier.DB
import com.example.pexelsvlad.domain.models.Photo
import javax.inject.Inject

class AddToBookmarksUseCase @Inject constructor(
    @DB private val dbPhotoRepositoryImpl: DBPhotoRepository
) {
    suspend operator fun invoke(photo: Photo){
        dbPhotoRepositoryImpl.addToBookmarks(photo)
    }
}