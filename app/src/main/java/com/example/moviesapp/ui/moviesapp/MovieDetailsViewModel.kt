package com.example.moviesapp.ui.moviesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.AppContextHolder
import com.example.moviesapp.data.LocalFavoriteMoviesRepositoryProvider
import com.example.moviesapp.data.RemoteMoviesRepositoryProvider
import com.example.moviesapp.model.Movie
import com.example.moviesapp.utils.toMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException

data class MovieDetailsUiState(val movie: Movie? = null)

class MovieDetailsViewModel(): ViewModel() {
    private val _movieDetailsUiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState())
    private val remoteRepository = RemoteMoviesRepositoryProvider.repository
    private val localRepository = LocalFavoriteMoviesRepositoryProvider.getRepository(AppContextHolder.appContext)
    val movieDetailsUiState: StateFlow<MovieDetailsUiState> = _movieDetailsUiState

    fun getMovie(movieId:Int){
        viewModelScope.launch {
            _movieDetailsUiState.value = try {
                val movie = localRepository.getFavorite(movieId)?.toMovie() ?:
                            remoteRepository.getMovie(movieId)
                MovieDetailsUiState(movie = movie)
            }
            catch(e: IOException){
                MovieDetailsUiState()
            }
        }
    }
}