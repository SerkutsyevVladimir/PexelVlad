package com.example.pexelsvlad.domain.mappers.rest

import com.example.pexelsvlad.data.network.models.RestCollection
import com.example.pexelsvlad.domain.mappers.Mapper
import com.example.pexelsvlad.domain.models.Collection

class RestCollectionMapper : Mapper<RestCollection, Collection> {
    override fun map(input: RestCollection): Collection {
        return Collection(
            id = input.id,
            title = input.title,
            description = input.description,
            private = input.private,
            mediaCount = input.mediaCount,
            photosCount = input.photosCount,
            videosCount = input.videosCount
        )
    }
}