package com.example.moviesapp.data

import com.example.moviesapp.network.MoviesApi

object RemoteMoviesRepositoryProvider {
    val repository: RemoteMoviesRepository by lazy {
        RemoteMoviesRepository(MoviesApi.retrofitService)
    }
}