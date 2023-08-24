package com.example.pexelsvlad.domain.models

data class Collection (
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val private: Boolean? = null,
    val mediaCount: String? = null,
    val photosCount: String? = null,
    val videosCount: String? = null
)