package com.example.pexelsvlad.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.domain.usecases.LoadSpecificPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val loadSpecificPhotoUseCase: LoadSpecificPhotoUseCase
):ViewModel(){

fun getPhotoDetails(id: String): Flow<Photo> = flow {
    emit(loadSpecificPhotoUseCase(id).getOrThrow())
}

}