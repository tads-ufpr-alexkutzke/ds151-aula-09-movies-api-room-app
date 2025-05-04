package com.example.moviesapp.ui.moviesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieDetailsScreen(
    movie: Movie = fourMovies[0],
    onGoBackClick: () -> Unit = {},
){
    val scrollState = rememberLazyListState()
    val density = LocalDensity.current

    val maxHeight = 500.dp
    val minHeight = 150.dp

    val maxHeightPx = with(density) { maxHeight.toPx() }
    val minHeightPx = with(density) { minHeight.toPx() }
    
    val scroll: Float =
        (scrollState.firstVisibleItemIndex * maxHeightPx + scrollState.firstVisibleItemScrollOffset)

    val currentHeightPx = (maxHeightPx - scroll).coerceIn(minHeightPx, maxHeightPx)
    val currentHeight = with(density) { currentHeightPx.toDp() }

    Box {
        LazyColumn(
            state = scrollState,
            contentPadding = PaddingValues(top = maxHeight),
        ) {
            items(5) {
                Column(modifier = Modifier
                    .padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text="Sinopse", style = MaterialTheme.typography.titleLarge)
                    Text(text = movie.synopsis)
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text="Diretor", style = MaterialTheme.typography.titleLarge)
                    Text(text = movie.director)
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text="Elenco", style = MaterialTheme.typography.titleLarge)
                    Text(text = movie.cast.toString())
                }
            }
        }

        Box {
            CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = "Poster de ${movie.title}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(currentHeight)
                        .align(Alignment.TopCenter)
                )
            }
            Text(
                text = movie.title,
                style = MaterialTheme.typography.displaySmall.copy(
                    shadow = Shadow(
                        color = Color.Black, offset = Offset(x = 0f,y = 0f), blurRadius = 10f
                    )
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.BottomStart)
            )
        }
    }
}

val previewHandler = AsyncImagePreviewHandler {
    ColorImage(Color.Red.toArgb())
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