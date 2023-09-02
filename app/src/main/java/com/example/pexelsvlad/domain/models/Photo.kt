package com.example.pexelsvlad.domain.models

import com.example.pexelsvlad.data.network.models.RestPhotoSrc

data class Photo(
    val id: String,
    val width: String? = null,
    val height: String? = null,
    val url: String? = null,
    val photographer: String? = null,
    val photographerUrl: String? = null,
    val photographerId: String? = null,
    val avgColor: String? = null,
    val src: RestPhotoSrc,
    val liked: Boolean? = null,
    val alt: String? = null
)