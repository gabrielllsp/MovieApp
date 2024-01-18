package com.gabrielalmeida.movieapp.presenter.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabrielalmeida.movieapp.BuildConfig
import com.gabrielalmeida.movieapp.data.mapper.toPresentation
import com.gabrielalmeida.movieapp.domain.usecase.movie.GetGenresUseCase
import com.gabrielalmeida.movieapp.domain.usecase.movie.GetMoviesByGenreUseCase
import com.gabrielalmeida.movieapp.util.Constants
import com.gabrielalmeida.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenreUseCase: GetGenresUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
) : ViewModel() {

    fun getGenres() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val genres = getGenreUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE
            ).map { it.toPresentation() }

            emit(StateView.Success(genres))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun getMoviesByGenre(genreId: Int?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val movies = getMoviesByGenreUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE,
                genreId = genreId

            )

            emit(StateView.Success(movies))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

}