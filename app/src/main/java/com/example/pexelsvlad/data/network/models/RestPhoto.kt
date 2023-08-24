package com.example.pexelsvlad.data.network.models

import com.google.gson.annotations.SerializedName

data class RestPhoto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("width") val width: String? = null,
    @SerializedName("height") val height: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("photographer") val photographer: String? = null,
    @SerializedName("photographer_url") val photographerUrl: String? = null,
    @SerializedName("photographer_id") val photographerId: String? = null,
    @SerializedName("avg_color") val avgColor: String? = null,
    @SerializedName("src") val src: RestPhotoSrc? = null,
    @SerializedName("liked") val liked: Boolean? = null,
    @SerializedName("alt") val alt: String? = null
)