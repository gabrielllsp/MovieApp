package com.gabrielalmeida.movieapp.domain.usecase.movie

import com.gabrielalmeida.movieapp.data.mapper.toDomain
import com.gabrielalmeida.movieapp.domain.model.Movie
import com.gabrielalmeida.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
){
    suspend operator fun invoke(apiKey: String, language: String?, movieId: Int?): Movie {
        return repository.getMovieDetails(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).toDomain()
    }
}