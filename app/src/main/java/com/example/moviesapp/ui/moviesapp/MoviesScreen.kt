package com.example.moviesapp.ui.moviesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    moviesListViewModel: MoviesListViewModel = hiltViewModel(),
    onGoToMovieDetailsClick: (movieId:Int) -> Unit = {},
) {
    val moviesScreenUiState = moviesListViewModel.moviesScreenUiState

    Column(){
        when(moviesScreenUiState){
            is MoviesScreenUiState.Success -> {
                MoviesList(
                    movies = moviesScreenUiState.movies,
                    favorites = moviesScreenUiState.favorites,
                    onFavoriteClick = { movieId -> moviesListViewModel.toggleFavorite(movieId) },
                    onMovieClick = {movieId -> onGoToMovieDetailsClick(movieId) },
                )
            }
            is MoviesScreenUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is MoviesScreenUiState.Error -> ErrorScreen( modifier = Modifier.fillMaxSize())
        }
    }
}