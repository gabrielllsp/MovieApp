package com.gabrielalmeida.movieapp.data.mapper

import com.gabrielalmeida.movieapp.data.model.CountryResponse
import com.gabrielalmeida.movieapp.data.model.GenreResponse
import com.gabrielalmeida.movieapp.data.model.MovieResponse
import com.gabrielalmeida.movieapp.domain.model.Country
import com.gabrielalmeida.movieapp.domain.model.Genre
import com.gabrielalmeida.movieapp.domain.model.Movie
import com.gabrielalmeida.movieapp.presenter.model.GenrePresentation

fun GenreResponse.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieResponse.toDomain(): Movie{
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        genres = genres?.map { it.toDomain() },
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        productionCountries = productionCountries?.map { it.toDomain()}
    )
}

fun Genre.toPresentation(): GenrePresentation {
    return GenrePresentation(
        id = id,
        name = name,
        movies = emptyList()
    )
}
    fun CountryResponse.toDomain() = Country(
        name = name
    )
