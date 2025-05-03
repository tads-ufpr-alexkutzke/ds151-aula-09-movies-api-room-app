package com.example.moviesapp.ui.moviesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MoviesScreen(
    moviesScreenUiState: MoviesScreenUiState,
    onGoToMovieDetailsClick: (movieId:Int) -> Unit = {},
){
    when(moviesScreenUiState){
        is MoviesScreenUiState.Success -> {
            MoviesList(
                movies = moviesScreenUiState.movies,
                onMovieClick = onGoToMovieDetailsClick
            )
        }
        is MoviesScreenUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is MoviesScreenUiState.Error -> ErrorScreen( modifier = Modifier.fillMaxSize())
    }
}
/*
@Preview
@Composable
fun MoviesScreenPreview(){
    MoviesAppTheme {
        MoviesScreen(
            movies = fourMovies,
            onGoToMovieDetailsClick = {},
        )
    }
}

*/