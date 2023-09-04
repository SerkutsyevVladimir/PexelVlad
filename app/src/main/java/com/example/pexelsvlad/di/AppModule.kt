package com.example.pexelsvlad.di

import com.example.pexelsvlad.data.network.api.PexelsApi
import com.example.pexelsvlad.domain.mappers.db.DBPhotoMapper
import com.example.pexelsvlad.domain.mappers.db.PhotoMapper
import com.example.pexelsvlad.domain.mappers.rest.RestCollectionMapper
import com.example.pexelsvlad.domain.mappers.rest.RestCollectionsSetMapper
import com.example.pexelsvlad.domain.mappers.rest.RestPhotoMapper
import com.example.pexelsvlad.domain.mappers.rest.RestPhotoSetMapper
import com.example.pexelsvlad.domain.mappers.rest.RestPhotoSrcMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePexelsApiInstance(): PexelsApi = PexelsApi.getInstance()

    @Provides
    @Singleton
    fun provideRestCollectionMapper(): RestCollectionMapper{
        return RestCollectionMapper()
    }

    @Provides
    @Singleton
    fun provideRestCollectionsSetMapper(
        restCollectionMapper: RestCollectionMapper
    ): RestCollectionsSetMapper{
        return RestCollectionsSetMapper(restCollectionMapper)
    }


    @Provides
    @Singleton
    fun provideRestPhotoSrcMapper(): RestPhotoSrcMapper{
        return RestPhotoSrcMapper()
    }

    @Provides
    @Singleton
    fun provideRestPhotoMapper(): RestPhotoMapper{
        return RestPhotoMapper()
    }

    @Provides
    @Singleton
    fun provideRestPhotoSetMapper(
        restPhotoMapper: RestPhotoMapper
    ): RestPhotoSetMapper{
        return RestPhotoSetMapper(restPhotoMapper)
    }

    @Provides
    @Singleton
    fun provideDBPhotoMapper(): DBPhotoMapper{
        return DBPhotoMapper()
    }

    @Provides
    @Singleton
    fun providePhotoMapper(): PhotoMapper {
        return PhotoMapper()
    }


}