package com.example.moviesapp.ui.moviesapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.fourMovies
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MoviesList(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit = {},
    onFavoriteClick: (Int) -> Unit = {},
    favorites: List<Movie> = emptyList<Movie>()
){
    LazyColumn(){
        items(movies) { movie ->
            MovieItem(
                onTitleClick = { onMovieClick(movie.id) },
                onFavoriteClick = { onFavoriteClick(movie.id) },
                movie = movie,
                isFavorite = favorites.find{favorite -> favorite.id == movie.id} != null
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