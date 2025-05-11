package com.example.moviesapp.data

import com.example.moviesapp.data.local.FavoriteMovieEntity
import com.example.moviesapp.data.local.FavoriteMovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalFavoriteMoviesRepository @Inject constructor(private val dao: FavoriteMovieDao) {
    suspend fun getAllFavorites(): List<FavoriteMovieEntity> = dao.getAll()
    suspend fun getFavorite(movieId: Int): FavoriteMovieEntity? = dao.getById(movieId)
    suspend fun isFavorite(movieId: Int): Boolean = dao.getById(movieId) != null
    suspend fun addFavorite(movie: FavoriteMovieEntity) = dao.insert(movie)
    suspend fun removeFavorite(movie: FavoriteMovieEntity) = dao.delete(movie)
}