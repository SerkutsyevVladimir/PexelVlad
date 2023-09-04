package com.example.pexelsvlad.domain.usecases

import com.example.pexelsvlad.di.qualifier.Rest
import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.repository.PhotoRepository
import javax.inject.Inject

class LoadCuratedPhotosUseCase @Inject constructor(
    @Rest private val restPhotoRepositoryImpl: PhotoRepository
) {
    suspend operator fun invoke(): Result<List<Photo>> {
        return restPhotoRepositoryImpl.getCuratedPhotosList()
    }
}