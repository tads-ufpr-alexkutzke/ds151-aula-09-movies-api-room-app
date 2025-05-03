package com.example.moviesapp.ui.moviesapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.fourMovies
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    isFavorite: Boolean = false,
    onTitleClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = modifier.clickable(
                onClick = onTitleClick,
            ),
            text = movie.title,
        )
        IconButton(
            onClick = onFavoriteClick,
        ) {
            if(isFavorite) {
                Icon(Icons.Filled.Favorite, contentDescription = "Toogle Favorite")
            }
            else{
                Icon(Icons.Filled.FavoriteBorder, contentDescription = "Toogle Favorite")
            }
        }
    }
}

@Preview
@Composable
fun MovieItemPreview(){
    MoviesAppTheme {
        MovieItem(movie = fourMovies[0])
    }
}