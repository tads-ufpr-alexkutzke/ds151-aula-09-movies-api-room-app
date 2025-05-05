package com.example.moviesapp.ui.moviesapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.fourMovies
import com.example.moviesapp.ui.theme.MoviesAppTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    isFavorite: Boolean = false,
    onTitleClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    val previewHandler = AsyncImagePreviewHandler{
        ColorImage(Color.Red.toArgb())
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides com.example.moviesapp.ui.moviesapp.previewHandler) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = "Poster de ${movie.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .align(Alignment.CenterVertically)
                    .padding(end = 10.dp)
            )
        }
        Text(
            modifier = modifier
                .clickable(
                onClick = onTitleClick,
            )
                .weight(1f),
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

