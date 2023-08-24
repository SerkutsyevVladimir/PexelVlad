package com.example.pexelsvlad.data.network.models

import com.google.gson.annotations.SerializedName

data class RestPhotoSrc(
    @SerializedName("original") val original: String? = null,
    @SerializedName("large2x") val large2x: String? = null,
    @SerializedName("large") val large: String? = null,
    @SerializedName("medium") val medium: String? = null,
    @SerializedName("small") val small: String? = null,
    @SerializedName("portrait") val portrait: String? = null,
    @SerializedName("landscape") val landscape: String? = null,
    @SerializedName("tiny") val tiny: String? = null
)