package com.gabrielalmeida.movieapp.data.mapper

import com.gabrielalmeida.movieapp.data.model.GenreResponse
import com.gabrielalmeida.movieapp.data.model.MovieResponse
import com.gabrielalmeida.movieapp.domain.model.Genre
import com.gabrielalmeida.movieapp.domain.model.Movie

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
        genreIds = genreIds,
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
        voteCount = voteCount


    )
}