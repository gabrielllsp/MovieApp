package com.gabrielalmeida.movieapp.presenter.main.moviegenre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabrielalmeida.movieapp.BuildConfig
import com.gabrielalmeida.movieapp.domain.usecase.movie.GetMoviesByGenreUseCase
import com.gabrielalmeida.movieapp.domain.usecase.movie.SearchMoviesUseCase
import com.gabrielalmeida.movieapp.util.Constants
import com.gabrielalmeida.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieGenreViewModel @Inject constructor(
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
): ViewModel() {

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

    fun searchMovies(query: String?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val movies = searchMoviesUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE,
                query = query

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