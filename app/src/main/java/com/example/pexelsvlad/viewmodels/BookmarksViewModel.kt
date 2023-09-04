package com.example.pexelsvlad.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.usecases.LoadListOfBookmarksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val loadListOfBookmarksUseCase: LoadListOfBookmarksUseCase
): ViewModel(){

    private val photoSharedFlow = MutableSharedFlow<Photo>(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun getAll(): Flow<List<Photo>>{
        return photoSharedFlow.map { loadListOfBookmarksUseCase().getOrDefault(emptyList()) }
            .onStart {
                emit(loadListOfBookmarksUseCase.invoke().getOrDefault(emptyList()))
            }
    }
}