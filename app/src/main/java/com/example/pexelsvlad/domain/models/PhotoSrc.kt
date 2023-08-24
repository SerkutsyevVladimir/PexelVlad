package com.example.pexelsvlad.domain.models

import com.google.gson.annotations.SerializedName

data class PhotoSrc(
    val original: String? = null,
    val large2x: String? = null,
    val large: String? = null,
    val medium: String? = null,
    val small: String? = null,
    val portrait: String? = null,
    val landscape: String? = null,
    val tiny: String? = null
)