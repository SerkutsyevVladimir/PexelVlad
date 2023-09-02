package com.example.pexelsvlad.domain.repository

import com.example.pexelsvlad.domain.models.Collection
import com.example.pexelsvlad.domain.models.CollectionsSet

interface CollectionRepository {
    suspend fun getCollectionsSet(): CollectionsSet

    suspend fun  getFeaturedCollectionsList(): Result<List<Collection>>
}