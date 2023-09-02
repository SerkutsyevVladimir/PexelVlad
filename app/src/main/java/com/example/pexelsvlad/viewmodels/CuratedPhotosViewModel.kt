package com.example.pexelsvlad.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pexelsvlad.domain.models.Collection
import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.usecases.LoadCuratedPhotosUseCase
import com.example.pexelsvlad.domain.usecases.LoadFeaturedCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CuratedPhotosViewModel @Inject constructor(
    private val loadCuratedPhotosUseCase: LoadCuratedPhotosUseCase,
    private val loadFeaturedCollectionsUseCase: LoadFeaturedCollectionsUseCase
) : ViewModel() {

    private val photoSharedFlow = MutableSharedFlow<Photo>(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val collectionsSharedFlow = MutableSharedFlow<Photo>(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun getAll(): Flow<List<Photo>> {
        return photoSharedFlow.map { loadCuratedPhotosUseCase().getOrDefault(emptyList()) }
            .onStart {
                emit(loadCuratedPhotosUseCase.invoke().getOrDefault(emptyList()))
            }
    }

    fun getAllCollections(): Flow<List<Collection>> {
        return collectionsSharedFlow.map { loadFeaturedCollectionsUseCase().getOrDefault(emptyList()) }
            .onStart {
                emit(loadFeaturedCollectionsUseCase.invoke().getOrDefault(emptyList()))
            }
    }
}