package com.example.moviesapp.data

import android.content.Context
import com.example.moviesapp.data.local.FavoriteMoviesDatabase

object LocalFavoriteMoviesRepositoryProvider {

    @Volatile private var instance: LocalFavoriteMoviesRepository? = null

    fun getRepository(context: Context): LocalFavoriteMoviesRepository {
        return instance ?: synchronized(this) {
            instance ?: LocalFavoriteMoviesRepository(
                FavoriteMoviesDatabase.getInstance(context).favoriteMovieDao()
            ).also { instance = it }
        }
    }
}