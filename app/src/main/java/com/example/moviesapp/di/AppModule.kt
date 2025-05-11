package com.example.moviesapp.di

import android.content.Context
import com.example.moviesapp.data.LocalFavoriteMoviesRepository
import com.example.moviesapp.data.RemoteMoviesRepository
import com.example.moviesapp.data.local.FavoriteMovieDao
import com.example.moviesapp.data.local.FavoriteMoviesDatabase
import com.example.moviesapp.network.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesApiService(): MoviesApiService {
        return Retrofit.Builder()
            .baseUrl("https://moviesapi.kutzke.com.br")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteMoviesRepository(apiService: MoviesApiService): RemoteMoviesRepository {
        return RemoteMoviesRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideFavoriteMoviesDatabase(@ApplicationContext context: Context): FavoriteMoviesDatabase {
        return FavoriteMoviesDatabase.getInstance(context)
    }

    @Provides
    fun provideFavoriteMovieDao(database: FavoriteMoviesDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }

    @Provides
    @Singleton
    fun provideLocalFavoriteMoviesRepository(favoriteMovieDao: FavoriteMovieDao): LocalFavoriteMoviesRepository {
        return LocalFavoriteMoviesRepository(favoriteMovieDao)
    }
}