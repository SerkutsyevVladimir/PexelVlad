package com.example.pexelsvlad.domain.mappers.rest

import com.example.pexelsvlad.data.network.models.RestPhotoSrc
import com.example.pexelsvlad.domain.mappers.Mapper
import com.example.pexelsvlad.domain.models.PhotoSrc

class RestPhotoSrcMapper : Mapper<RestPhotoSrc, PhotoSrc> {
    override fun map(input: RestPhotoSrc): PhotoSrc {
        return PhotoSrc(
            original = input.original,
            large2x = input.large2x,
            large = input.large,
            medium = input.medium,
            small = input.small,
            portrait = input.portrait,
            landscape = input.landscape,
            tiny = input.tiny
        )
    }
}