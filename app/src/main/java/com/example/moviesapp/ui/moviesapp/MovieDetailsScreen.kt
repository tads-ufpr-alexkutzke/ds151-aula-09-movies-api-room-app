package com.example.moviesapp.ui.moviesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    moviesAppViewModel: MoviesAppViewModel = viewModel(),
    movieDetailsScreenUiState: MovieDetailsScreenUiState,
    onGoBackClick: () -> Unit = {},
){
    when(movieDetailsScreenUiState){
        is MovieDetailsScreenUiState.Success -> {
            MovieDetailsScreen(movie = movieDetailsScreenUiState.movie)
        }
        is MovieDetailsScreenUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is MovieDetailsScreenUiState.Error -> ErrorScreen( modifier = Modifier.fillMaxSize())
    }

    LaunchedEffect(movieId) {
        moviesAppViewModel.getMovie(movieId)
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
fun MovieDetailsScreenPreview(){
    MoviesAppTheme {
       MovieDetailsScreen(
           movie = fourMovies[0]
       )
    }
}