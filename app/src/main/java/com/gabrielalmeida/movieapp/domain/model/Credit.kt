package com.gabrielalmeida.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credit(

    val cast: List<Person>?
) : Parcelable
