package com.gabrielalmeida.movieapp.domain.usecase.movie

import com.gabrielalmeida.movieapp.data.mapper.toDomain
import com.gabrielalmeida.movieapp.domain.model.Credit
import com.gabrielalmeida.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetCreditsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
){
    suspend operator fun invoke(apiKey: String, language: String?, movieId: Int?): Credit {
        return repository.getCredits(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).toDomain()
    }
}