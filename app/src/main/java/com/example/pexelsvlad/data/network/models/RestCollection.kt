package com.example.pexelsvlad.data.network.models

import com.google.gson.annotations.SerializedName

data class RestCollection(
    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("private") val private: Boolean? = null,
    @SerializedName("media_count") val mediaCount: String? = null,
    @SerializedName("photos_count") val photosCount: String? = null,
    @SerializedName("videos_count") val videosCount: String? = null
)