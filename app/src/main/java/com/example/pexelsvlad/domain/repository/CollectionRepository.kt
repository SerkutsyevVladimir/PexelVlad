package com.example.pexelsvlad.domain.repository

import com.example.pexelsvlad.domain.models.CollectionsSet

interface CollectionRepository {
    suspend fun getFeaturedCollections(): CollectionsSet
}