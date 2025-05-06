package com.example.moviesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "favorite_movies")
@TypeConverters(CastConverter::class)
data class FavoriteMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val cast: List<String>,
    val director: String,
    val synopsis: String,
    val posterUrl: String
)