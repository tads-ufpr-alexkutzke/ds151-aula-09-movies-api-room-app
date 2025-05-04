package com.example.moviesapp.ui.moviesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.AppContextHolder
import com.example.moviesapp.data.LocalFavoriteMoviesRepositoryProvider
import com.example.moviesapp.data.RemoteMoviesRepositoryProvider
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.Review
import com.example.moviesapp.utils.toMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException

sealed interface MovieDetailsUiState {
    class Success(val movie: Movie?, val reviews: List<Review>): MovieDetailsUiState
    class SuccessButNoReviews(val movie: Movie?, val reviews: List<Review>?): MovieDetailsUiState
    object Error: MovieDetailsUiState
    object Loading: MovieDetailsUiState
}

class MovieDetailsViewModel(): ViewModel() {
    private val remoteRepository = RemoteMoviesRepositoryProvider.repository
    private val localRepository = LocalFavoriteMoviesRepositoryProvider.getRepository(AppContextHolder.appContext)
    var movieDetailsUiState: MovieDetailsUiState by mutableStateOf(MovieDetailsUiState.Loading)

    fun getMovie(movieId:Int){
        var movie: Movie?
        var reviews: List<Review>

        movie = null

        viewModelScope.launch {
            movieDetailsUiState = try {
                movie = localRepository.getFavorite(movieId)?.toMovie() ?:
                        remoteRepository.getMovie(movieId)
                reviews = remoteRepository.getReviews(movieId)
                MovieDetailsUiState.Success(movie = movie, reviews= reviews)
            }
            catch(e: IOException) {
                try {
                    movie = localRepository.getFavorite(movieId)?.toMovie()
                        ?: remoteRepository.getMovie(movieId)
                    MovieDetailsUiState.SuccessButNoReviews(movie = movie, reviews = null)

                } catch (e: IOException) {
                    MovieDetailsUiState.Error
                }
            }
        }
    }
}