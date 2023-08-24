package com.example.pexelsvlad.domain.models

data class CollectionsSet(
    val page: String? = null,
    val perPage: String? = null,
    val collections: List<Collection> = emptyList(),
    val totalResults: String? = null,
    val nextPage: String? = null
)