package com.example.moviesapp.ui.moviesapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MoviesList(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit = {},
){
    LazyColumn(){
        items(movies) { movie ->
            MovieItem(
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = { onMovieClick(movie.id) },
                ),
                movie = movie,
            )
        }
    }
}

@Preview
@Composable
fun MoviesListPreview(){
    MoviesAppTheme {
        MoviesList(
            movies = fourMovies,
        )
    }
}