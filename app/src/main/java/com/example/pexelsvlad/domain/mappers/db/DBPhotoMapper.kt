package com.example.pexelsvlad.domain.mappers.db

import com.example.pexelsvlad.data.database.models.DBPhoto
import com.example.pexelsvlad.data.network.models.RestPhotoSrc
import com.example.pexelsvlad.domain.mappers.Mapper
import com.example.pexelsvlad.domain.models.Photo

class DBPhotoMapper : Mapper<DBPhoto, Photo> {
    override fun map(input: DBPhoto): Photo {
        return Photo(
            id = input.id,
            width = input.width,
            height = input.height,
            url = input.url,
            photographer = input.photographer,
            photographerUrl = input.photographerUrl,
            photographerId = input.photographerId,
            avgColor = input.avgColor,
            src = RestPhotoSrc(
                original = input.src,
                large2x = null,
                large = null,
                medium = null,
                small = null,
                portrait = null,
                landscape = null,
                tiny = null
            ),
            liked = input.liked,
            alt = input.alt
        )
    }
}


class PhotoMapper : Mapper<Photo, DBPhoto> {
    override fun map(input: Photo): DBPhoto {
        return DBPhoto(
            id = input.id,
            width = input.width,
            height = input.height,
            url = input.url,
            photographer = input.photographer,
            photographerUrl = input.photographerUrl,
            photographerId = input.photographerId,
            avgColor = input.avgColor,
            src = input.src.original,
            liked = true,
            alt = input.alt
        )
    }
}

