package com.example.moviesapp.ui.moviesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.network.MoviesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okio.IOException

sealed interface MoviesScreenUiState {
    class Success(val movies: List<Movie>): MoviesScreenUiState
    object Error: MoviesScreenUiState
    object Loading: MoviesScreenUiState
}

sealed interface MovieDetailsScreenUiState {
    class Success(val movie: Movie ) : MovieDetailsScreenUiState
    object Error : MovieDetailsScreenUiState
    object Loading : MovieDetailsScreenUiState
}

class MoviesAppViewModel(val fake: Boolean = false): ViewModel() {

    var moviesScreenUiState: MoviesScreenUiState by mutableStateOf(MoviesScreenUiState.Loading)
    var movieDetailsScreenUiState: MovieDetailsScreenUiState by mutableStateOf(MovieDetailsScreenUiState.Loading)

    init {
        getMovies()
    }

    private fun getMovies(){
        viewModelScope.launch {
            moviesScreenUiState = try {
               val movies = MoviesApi.retrofitService.getMovies()
                MoviesScreenUiState.Success(movies = movies)
            }
            catch(e: IOException){
                MoviesScreenUiState.Error
            }
        }
    }

    fun getMovie(movieId:Int) {
        movieDetailsScreenUiState = MovieDetailsScreenUiState.Loading
        viewModelScope.launch {
            movieDetailsScreenUiState = try{
                delay(2000)
                val movie = MoviesApi.retrofitService.getMovie(movieId)
                MovieDetailsScreenUiState.Success(movie = movie)
            }
            catch(e: IOException) {
                MovieDetailsScreenUiState.Error
            }
        }
    }
}