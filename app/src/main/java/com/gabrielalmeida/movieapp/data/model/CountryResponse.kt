package com.gabrielalmeida.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("name")
    val name: String?
)
