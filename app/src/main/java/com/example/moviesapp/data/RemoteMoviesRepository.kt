package com.example.moviesapp.data

import com.example.moviesapp.network.MoviesApiService
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.Review

class RemoteMoviesRepository(
    private val apiService: MoviesApiService
) {
    suspend fun getMovies(): List<Movie> {
        return apiService.getMovies()
    }

    suspend fun getMovie(id: Int): Movie {
        return apiService.getMovie(id)
    }

    suspend fun getReviews(movieId: Int): List<Review> {
        return apiService.getReviews(movieId)
    }
}