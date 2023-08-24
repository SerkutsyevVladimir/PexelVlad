package com.example.pexelsvlad.domain.mappers.rest

import com.example.pexelsvlad.data.network.models.RestPhotoSet
import com.example.pexelsvlad.domain.mappers.Mapper
import com.example.pexelsvlad.domain.models.PhotoSet

class RestPhotoSetMapper : Mapper<RestPhotoSet, PhotoSet> {
    override fun map(input: RestPhotoSet): PhotoSet {
        return PhotoSet(
            page = input.page,
            perPage = input.perPage,
            photos = input.photos,
            totalResults = input.totalResults,
            nextPage = input.nextPage
        )
    }
}