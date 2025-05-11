package com.example.moviesapp.ui.moviesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.LocalFavoriteMoviesRepository
import com.example.moviesapp.data.RemoteMoviesRepository
import com.example.moviesapp.model.Movie
import com.example.moviesapp.utils.toFavoriteEntity
import com.example.moviesapp.utils.toMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

sealed interface MoviesScreenUiState {
    class Success(val movies: List<Movie>, val favorites: List<Movie>): MoviesScreenUiState
    object Error: MoviesScreenUiState
    object Loading: MoviesScreenUiState
}

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val remoteRepository: RemoteMoviesRepository,
    private val localRepository: LocalFavoriteMoviesRepository
) : ViewModel() {
    var moviesScreenUiState: MoviesScreenUiState by mutableStateOf(MoviesScreenUiState.Loading)

    init {
        getMovies()
    }

    private fun getMovies(){
        viewModelScope.launch {
            moviesScreenUiState = try {
                val movies = remoteRepository.getMovies()
                val favorites = localRepository.getAllFavorites().map { it.toMovie() }
                MoviesScreenUiState.Success(movies = movies, favorites = favorites)
            }
            catch(e: IOException){
                MoviesScreenUiState.Error
            }
        }
    }

    fun toggleFavorite(movieId:Int){
        if(moviesScreenUiState is MoviesScreenUiState.Success){
            val movieToToggle: Movie? = (moviesScreenUiState as MoviesScreenUiState.Success).movies.find{ movie -> movieId == movie.id}
            movieToToggle?.let{
                viewModelScope.launch {
                    if(localRepository.getFavorite(movieId) == null){
                        localRepository.addFavorite(movieToToggle.toFavoriteEntity())
                    }
                    else{
                        localRepository.removeFavorite(movieToToggle.toFavoriteEntity())
                    }
                    val favorites = localRepository.getAllFavorites().map { it.toMovie() }
                    moviesScreenUiState = MoviesScreenUiState.Success(movies = (moviesScreenUiState as MoviesScreenUiState.Success).movies, favorites = favorites)
                }
            }
        }
    }
}