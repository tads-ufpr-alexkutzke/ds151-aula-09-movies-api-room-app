package com.example.moviesapp.ui.moviesapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
) {
    Text(
        modifier = modifier,
        text = movie.title,
    )
}

@Preview
@Composable
fun MovieItemPreview(){
    MoviesAppTheme {
        MovieItem(movie = fourMovies[0])
    }
}