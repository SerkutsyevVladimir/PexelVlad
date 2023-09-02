package com.example.pexelsvlad.data.repository.db

import com.example.pexelsvlad.data.database.dao.DBPhotoDao
import com.example.pexelsvlad.domain.mappers.db.DBPhotoMapper
import com.example.pexelsvlad.domain.mappers.db.PhotoMapper
import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.models.PhotoSet
import com.example.pexelsvlad.domain.repository.PhotoRepository
import javax.inject.Inject


interface DBPhotoRepository : PhotoRepository {

    override suspend fun getCuratedPhotosList(): Result<List<Photo>>

    override suspend fun getSpecificPhoto(id: String): Result<Photo>

    suspend fun addToBookmarks(photo: Photo)

    suspend fun removeFromBookmarks(id: String)
}


class DBPhotoRepositoryImpl @Inject constructor(
    private val photoDao: DBPhotoDao,
    private val dbPhotoMapper: DBPhotoMapper,
    private val photoMapper: PhotoMapper
) : DBPhotoRepository {
    override suspend fun getCuratedPhotosList(): Result<List<Photo>> {
        return runCatching {
            photoDao.getAllPhotos().map(dbPhotoMapper::map)
        }
    }

    override suspend fun getSpecificPhoto(id: String): Result<Photo> {
        return runCatching {
            photoDao.findById(id).let {
                dbPhotoMapper.map(it)
            }
        }
    }

    override suspend fun addToBookmarks(photo: Photo) {
        photoDao.addPhoto(photo.let { photoMapper.map(it) })
    }

    override suspend fun removeFromBookmarks(id: String) {
        photoDao.deletePhoto(id)
    }

    override suspend fun getCuratedPhotoSet(): PhotoSet {
        TODO("Not yet implemented")
    }
}