package com.example.pexelsvlad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.usecases.AddToBookmarksUseCase
import com.example.pexelsvlad.domain.usecases.LoadSpecificBookmarkUseCase
import com.example.pexelsvlad.domain.usecases.LoadSpecificPhotoUseCase
import com.example.pexelsvlad.domain.usecases.RemoveFromBookmarksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val loadSpecificPhotoUseCase: LoadSpecificPhotoUseCase,
    private val addToBookmarksUseCase: AddToBookmarksUseCase,
    private val removeFromBookmarksUseCase: RemoveFromBookmarksUseCase,
    private val loadSpecificBookmarkUseCase: LoadSpecificBookmarkUseCase
) : ViewModel() {

    fun getPhotoDetails(id: String): Flow<Photo> = flow {
        emit(loadSpecificPhotoUseCase(id).getOrThrow())
    }

    fun getBookmarkDetails(id: String): Flow<Photo> = flow{
        emit(loadSpecificBookmarkUseCase(id).getOrThrow())
    }

    fun addToBookmarks(photo: Photo) {
        viewModelScope.launch {
            addToBookmarksUseCase.invoke(photo)
        }
    }

    fun removeFromBookmarks(id: String) {
        viewModelScope.launch {
            removeFromBookmarksUseCase.invoke(id)
        }
    }

}