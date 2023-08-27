package com.example.pexelsvlad.di

import com.example.pexelsvlad.data.network.api.PexelsApi
import com.example.pexelsvlad.data.repository.rest.RestCollectionRepositoryImpl
import com.example.pexelsvlad.data.repository.rest.RestPhotoRepositoryImpl
import com.example.pexelsvlad.di.qualifier.Rest
import com.example.pexelsvlad.domain.mappers.rest.RestCollectionsSetMapper
import com.example.pexelsvlad.domain.mappers.rest.RestPhotoMapper
import com.example.pexelsvlad.domain.mappers.rest.RestPhotoSetMapper
import com.example.pexelsvlad.domain.repository.CollectionRepository
import com.example.pexelsvlad.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {



    @Provides
    @Singleton
    @Rest
    fun provideRestCollectionRepository(
        pexelsApi: PexelsApi,
        restCollectionsSetMapper: RestCollectionsSetMapper
    ): CollectionRepository{
        return RestCollectionRepositoryImpl(pexelsApi,restCollectionsSetMapper)
    }

    @Provides
    @Singleton
    @Rest
    fun provideRestPhotoRepository(
        pexelsApi: PexelsApi,
        restPhotoMapper: RestPhotoMapper,
        restPhotoSetMapper: RestPhotoSetMapper
    ): PhotoRepository{
        return RestPhotoRepositoryImpl(pexelsApi, restPhotoMapper, restPhotoSetMapper)
    }

}