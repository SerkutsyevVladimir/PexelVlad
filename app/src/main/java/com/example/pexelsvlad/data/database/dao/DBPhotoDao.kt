package com.example.pexelsvlad.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pexelsvlad.data.database.models.DBPhoto

@Dao
interface DBPhotoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPhoto(photo: DBPhoto)

    @Query("DELETE FROM ${DBPhoto.TABLE_NAME} WHERE ${DBPhoto.PHOTO_ID} = :id")
    suspend fun deletePhoto(id: String)

    @Query("SELECT * FROM ${DBPhoto.TABLE_NAME}")
    suspend fun getAllPhotos(): List<DBPhoto>

    @Query("SELECT * FROM ${DBPhoto.TABLE_NAME} WHERE ${DBPhoto.PHOTO_ID} = :id" )
    suspend fun findById(id: String): DBPhoto


}