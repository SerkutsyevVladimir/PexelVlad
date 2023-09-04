package com.example.pexelsvlad.domain.usecases

import com.example.pexelsvlad.di.qualifier.Rest
import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.repository.PhotoRepository
import javax.inject.Inject

class LoadSpecificPhotoUseCase @Inject constructor(
    @Rest private val restPhotoRepositoryImpl: PhotoRepository
) {

    suspend operator fun invoke(id: String): Result<Photo>{
        return restPhotoRepositoryImpl.getSpecificPhoto(id)
    }
}