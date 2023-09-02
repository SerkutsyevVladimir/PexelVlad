package com.example.pexelsvlad.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pexelsvlad.data.database.models.DBCollection.Companion.TABLE_NAME


@Entity(
    tableName = TABLE_NAME
)
data class DBCollection(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: String? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "private") val private: Boolean? = null,
    @ColumnInfo(name = "media_count") val mediaCount: String? = null,
    @ColumnInfo(name = "photos_count") val photosCount: String? = null,
    @ColumnInfo(name = "videos_count") val videosCount: String? = null
) {

    companion object {
        const val TABLE_NAME = "Collection"
    }
}