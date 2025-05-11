package com.example.moviesapp.data

import android.content.Context
import com.example.moviesapp.data.local.FavoriteMoviesDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalFavoriteMoviesRepositoryProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var instance: LocalFavoriteMoviesRepository? = null

    fun getRepository(): LocalFavoriteMoviesRepository {
        return instance ?: synchronized(this) {
            instance ?: LocalFavoriteMoviesRepository(
                FavoriteMoviesDatabase.getInstance(context).favoriteMovieDao()
            ).also { instance = it }
        }
    }
}