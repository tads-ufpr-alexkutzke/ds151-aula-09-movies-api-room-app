package com.example.moviesapp.ui.moviesapp

class Movie(
    val id: Int,
    val title: String,
    val cast: List<String>,
    val director: String,
    val synopsis: String,
    val posterUrl: String,
) {
}

val fourMovies: List<Movie> = listOf(
    Movie(
        id = 1,
        title = "Um Sonho de Liberdade",
        cast = listOf("Tim Robbins", "Morgan Freeman", "William Sadler"),
        director = "Frank Darabont",
        synopsis = "Acusado injustamente de assassinato, Andy Dufresne encontra esperança e redenção na prisão de Shawshank.",
        posterUrl = "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
    ),
    Movie(
        id = 2,
        title = "O Poderoso Chefão",
        cast = listOf("Marlon Brando", "Al Pacino", "James Caan"),
        director = "Francis Ford Coppola",
        synopsis = "A trajetória da família mafiosa Corleone e os desafios enfrentados no submundo do crime.",
        posterUrl = "https://image.tmdb.org/t/p/w500/d4KNaTrltq6bpkFS01pYtyXa09m.jpg",
    ),
    Movie(
        id = 3,
        title = "O Poderoso Chefão II",
        cast = listOf("Al Pacino", "Robert De Niro", "Robert Duvall"),
        director = "Francis Ford Coppola",
        synopsis = "Expande a saga dos Corleone, explorando passado e presente da família mafiosa.",
        posterUrl = "https://image.tmdb.org/t/p/w500/amvmeQWheahG3StKwIE1f7jRnkZ.jpg",
    ),
    Movie(
        id = 4,
        title = "Batman: O Cavaleiro das Trevas",
        cast = listOf("Christian Bale", "Heath Ledger", "Aaron Eckhart"),
        director = "Christopher Nolan",
        synopsis = "O Coringa ameaça destruir Gotham, e Batman precisa lidar com caos e sacrifícios pessoais.",
        posterUrl = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
    ),
)