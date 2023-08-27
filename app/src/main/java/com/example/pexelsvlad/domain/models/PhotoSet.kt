package com.example.pexelsvlad.domain.models

import com.example.pexelsvlad.data.network.models.RestPhoto
import com.google.gson.annotations.SerializedName

class PhotoSet(
    val page: String? = null,
    val perPage: String? = null,
    val photos: List<Photo> = emptyList(),
    val totalResults: String? = null,
    val nextPage: String? = null
)