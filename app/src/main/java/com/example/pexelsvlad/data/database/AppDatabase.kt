package com.example.pexelsvlad.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pexelsvlad.data.database.dao.DBPhotoDao
import com.example.pexelsvlad.data.database.models.DBPhoto


@Database(
    version = 1,
    entities = [
        DBPhoto::class
    ]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getDBPhotoDao(): DBPhotoDao
}