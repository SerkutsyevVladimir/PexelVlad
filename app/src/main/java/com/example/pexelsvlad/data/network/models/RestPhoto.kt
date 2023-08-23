package com.example.pexelsvlad.data.network.models

import com.google.gson.annotations.SerializedName

data class RestPhoto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("photographer") var photographer: String? = null
)