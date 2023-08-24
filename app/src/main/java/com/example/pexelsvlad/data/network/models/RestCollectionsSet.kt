package com.example.pexelsvlad.data.network.models

import com.google.gson.annotations.SerializedName

data class RestCollectionsSet(
    @SerializedName("page") val page: String? = null,
    @SerializedName("per_page") val perPage: String? = null,
    @SerializedName("collections") val collections: List<RestCollection>? = emptyList(),
    @SerializedName("total_results") val totalResults: String? = null,
    @SerializedName("next_page") val nextPage: String? = null
)