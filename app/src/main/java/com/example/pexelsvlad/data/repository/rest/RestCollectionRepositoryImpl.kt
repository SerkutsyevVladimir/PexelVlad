package com.example.pexelsvlad.data.repository.rest

import com.example.pexelsvlad.data.network.api.PexelsApi
import com.example.pexelsvlad.domain.mappers.rest.RestCollectionsSetMapper
import com.example.pexelsvlad.domain.models.Collection
import com.example.pexelsvlad.domain.models.CollectionsSet
import com.example.pexelsvlad.domain.repository.CollectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestCollectionRepositoryImpl @Inject constructor(
    private val pexelsApi: PexelsApi,
    private val restCollectionsSetMapper: RestCollectionsSetMapper
) : CollectionRepository {

    override suspend fun getCollectionsSet(): CollectionsSet = withContext(Dispatchers.IO) {
        val restCollectionsSet = pexelsApi.getFeaturedCollections()
        return@withContext restCollectionsSet.let { restCollectionsSetMapper.map(it) }
    }

    override suspend fun getFeaturedCollectionsList(): List<Collection> =
        withContext(Dispatchers.IO) {
            val collectionsSet = getCollectionsSet()
            return@withContext collectionsSet.collections
        }
}