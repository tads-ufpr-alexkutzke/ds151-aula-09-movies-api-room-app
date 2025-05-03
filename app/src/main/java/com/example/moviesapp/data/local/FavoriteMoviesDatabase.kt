package com.example.moviesapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FavoriteMovieEntity::class], version = 1)
@TypeConverters(CastConverter::class)
abstract class FavoriteMoviesDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object {
        @Volatile private var INSTANCE: FavoriteMoviesDatabase? = null

        fun getInstance(context: Context): FavoriteMoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteMoviesDatabase::class.java,
                    "favorites.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}