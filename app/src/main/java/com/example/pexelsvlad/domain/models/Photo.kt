package com.example.pexelsvlad.domain.models

import com.example.pexelsvlad.data.network.models.RestPhotoSrc
import com.google.gson.annotations.SerializedName

data class Photo(
    val id: String? = null,
    val width: String? = null,
    val height: String? = null,
    val url: String? = null,
    val photographer: String? = null,
    val photographerUrl: String? = null,
    val photographerId: String? = null,
    val avgColor: String? = null,
    val src: RestPhotoSrc? = null,
    val liked: Boolean? = null,
    val alt: String? = null
)