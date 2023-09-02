package com.example.pexelsvlad.domain.usecases

import com.example.pexelsvlad.di.qualifier.Rest
import com.example.pexelsvlad.domain.models.Collection
import com.example.pexelsvlad.domain.repository.CollectionRepository
import javax.inject.Inject

class LoadFeaturedCollectionsUseCase @Inject constructor(
    @Rest private val restCollectionRepositoryImpl: CollectionRepository
) {
//    suspend fun loadfeaturedcollections(): List<Collection> {
//        return restCollectionRepositoryImpl.getFeaturedCollectionsList()
//    }

    suspend operator fun invoke(): Result<List<Collection>> {
        return restCollectionRepositoryImpl.getFeaturedCollectionsList()
    }

}