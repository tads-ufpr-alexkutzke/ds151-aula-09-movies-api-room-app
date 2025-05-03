package com.example.moviesapp.ui.moviesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.fourMovies
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    movieDetailsViewModel: MovieDetailsViewModel = viewModel(),
    onGoBackClick: () -> Unit = {},
){
    val movieDetailsUiState = movieDetailsViewModel.movieDetailsUiState.collectAsState()

    LaunchedEffect(movieId) {
        movieDetailsViewModel.getMovie(movieId)
    }

    if(movieDetailsUiState.value.movie == null){
        ErrorScreen( modifier = Modifier.fillMaxSize())
    }
    else{
        MovieDetailsScreen(movie = movieDetailsUiState.value.movie!!)
    }
}

@Composable
fun MovieDetailsScreen(
    movie: Movie = fourMovies[0],
    onGoBackClick: () -> Unit = {},
){
    MovieItem(movie = movie)
}


@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MoviesAppTheme {
        MovieDetailsScreen(
            movie = fourMovies[0]
        )
    }
}