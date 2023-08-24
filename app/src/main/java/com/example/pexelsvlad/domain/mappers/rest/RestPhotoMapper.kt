package com.example.pexelsvlad.domain.mappers.rest

import com.example.pexelsvlad.data.network.models.RestPhoto
import com.example.pexelsvlad.domain.mappers.Mapper
import com.example.pexelsvlad.domain.models.Photo

class RestPhotoMapper : Mapper<RestPhoto, Photo> {
    override fun map(input: RestPhoto): Photo {
        return Photo(
            id = input.id,
            width = input.width,
            height = input.height,
            url = input.url,
            photographer = input.photographer,
            photographerUrl = input.photographerUrl,
            photographerId = input.photographerId,
            avgColor = input.avgColor,
            src = input.src,
            liked = input.liked,
            alt = input.alt
        )
    }
}