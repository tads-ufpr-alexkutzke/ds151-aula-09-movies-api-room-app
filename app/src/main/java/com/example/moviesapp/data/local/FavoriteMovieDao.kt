package com.example.moviesapp.data.local

import androidx.room.*

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorite_movies")
    suspend fun getAll(): List<FavoriteMovieEntity>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    suspend fun getById(movieId: Int): FavoriteMovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: FavoriteMovieEntity)

    @Delete
    suspend fun delete(movie: FavoriteMovieEntity)
}