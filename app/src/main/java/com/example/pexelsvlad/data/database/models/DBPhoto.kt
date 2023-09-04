package com.example.pexelsvlad.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pexelsvlad.data.database.models.DBPhoto.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class DBPhoto(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = PHOTO_ID) val id: String,
    @ColumnInfo(name = "width") val width: String? = null,
    @ColumnInfo(name = "height") val height: String? = null,
    @ColumnInfo(name = "url") val url: String? = null,
    @ColumnInfo(name = "photographer") val photographer: String,
    @ColumnInfo(name = "photographerUrl") val photographerUrl: String? = null,
    @ColumnInfo(name = "photographerId") val photographerId: String? = null,
    @ColumnInfo(name = "avg_color") val avgColor: String? = null,
    @ColumnInfo(name = "original_src") val src: String? = null,
    @ColumnInfo(name = "liked") val liked: Boolean? = null,
    @ColumnInfo(name = "alt") val alt: String? = null
) {



    companion object {
        const val TABLE_NAME = "Photo"
        const val PHOTO_ID = "id"
    }
}