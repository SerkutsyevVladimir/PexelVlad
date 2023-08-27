package com.example.pexelsvlad.data.repository.rest

import com.example.pexelsvlad.data.network.api.PexelsApi
import com.example.pexelsvlad.data.network.models.RestPhoto
import com.example.pexelsvlad.domain.mappers.rest.RestPhotoMapper
import com.example.pexelsvlad.domain.mappers.rest.RestPhotoSetMapper
import com.example.pexelsvlad.domain.mappers.rest.RestPhotoSrcMapper
import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.models.PhotoSet
import com.example.pexelsvlad.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestPhotoRepositoryImpl @Inject constructor(
    private val pexelsApi: PexelsApi,
    private val restPhotoMapper: RestPhotoMapper,
    private val restPhotoSetMapper: RestPhotoSetMapper
): PhotoRepository {

    override suspend fun getCuratedPhotoSet(): PhotoSet = withContext(Dispatchers.IO){
        val restCuratedPhotoSet = pexelsApi.getCuratedPhotos()
        return@withContext restCuratedPhotoSet.let { restPhotoSetMapper.map(it) }
    }

    override suspend fun getCuratedPhotosList(): List<Photo> = withContext(Dispatchers.IO){
        val curatedPhotoSet = getCuratedPhotoSet()
        return@withContext curatedPhotoSet.photos
    }

    override suspend fun getSpecificPhoto(id: String): Photo = withContext(Dispatchers.IO) {
        return@withContext pexelsApi.getSpecificPhoto(id)
            .let<RestPhoto, Photo> { restPhotoMapper.map(it) }
    }
}