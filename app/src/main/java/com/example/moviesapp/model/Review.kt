package com.example.moviesapp.model

class Review(
    val id: Int,
    val movieId: Int,
    val author: String,
    val reviewText: String,
    val rating: Int,
) {
}

val fourReviews: List<Review> = listOf(
    Review(
        id = 1,
        movieId = 1,
        author = "João Silva",
        reviewText = "Uma história inspiradora sobre esperança, amizade e redenção. Atuações impecáveis de Tim Robbins e Morgan Freeman.",
        rating = 10
    ),
    Review(
        id = 2,
        movieId = 1,
        author = "Ana Souza",
        reviewText = "O filme emociona do começo ao fim, com uma narrativa envolvente e um final perfeito.",
        rating = 9
    ),
        Review(
        id = 3,
        movieId = 1,
        author = "Carlos Mendes",
        reviewText = "Roteiro cativante e personagens profundos. Um clássico absoluto do cinema.",
        rating = 10
    ),
)

