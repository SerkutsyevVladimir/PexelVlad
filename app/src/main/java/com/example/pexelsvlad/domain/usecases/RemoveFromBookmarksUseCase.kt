package com.example.pexelsvlad.domain.usecases

import com.example.pexelsvlad.data.repository.db.DBPhotoRepository
import com.example.pexelsvlad.di.qualifier.DB
import javax.inject.Inject

class RemoveFromBookmarksUseCase @Inject constructor(
    @DB private val dbPhotoRepositoryImpl: DBPhotoRepository
) {

    suspend operator fun invoke(id: String){
        dbPhotoRepositoryImpl.removeFromBookmarks(id)
    }
}