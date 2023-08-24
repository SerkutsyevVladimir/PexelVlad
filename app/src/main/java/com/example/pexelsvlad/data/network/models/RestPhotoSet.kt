package com.example.pexelsvlad.data.network.models

import com.google.gson.annotations.SerializedName

data class RestPhotoSet(
    @SerializedName("page") val page: String? = null,
    @SerializedName("per_page") val perPage: String? = null,
    @SerializedName("photos") val photos: List<RestPhoto>? = emptyList(),
    @SerializedName("total_results") val totalResults: String? = null,
    @SerializedName("next_page") val nextPage: String? = null
)