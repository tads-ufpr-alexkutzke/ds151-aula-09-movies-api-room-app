package com.example.moviesapp.network

import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.Review
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://moviesapi.kutzke.com.br"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {
    @GET("/movies")
    suspend fun getMovies(): List<Movie>

    @GET("/movies/{id}")
    suspend fun getMovie(@Path("id") id:Int): Movie

    @GET("/movie/{movieId}/reviews")
    suspend fun getReviews(@Path("movieId") movieId:Int): List<Review>
}

object MoviesApi {
    val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}