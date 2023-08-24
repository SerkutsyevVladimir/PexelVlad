package com.example.pexelsvlad.domain.mappers.rest

import com.example.pexelsvlad.data.network.models.RestCollectionsSet
import com.example.pexelsvlad.domain.mappers.Mapper
import com.example.pexelsvlad.domain.models.CollectionsSet

class RestCollectionsSetMapper(private val restCollectionMapper: RestCollectionMapper) :
    Mapper<RestCollectionsSet, CollectionsSet> {
    override fun map(input: RestCollectionsSet): CollectionsSet {
        return CollectionsSet(
            page = input.page,
            perPage = input.perPage,
            collections = input.collections.orEmpty().map(restCollectionMapper::map),
            totalResults = input.totalResults,
            nextPage = input.nextPage
        )
    }
}