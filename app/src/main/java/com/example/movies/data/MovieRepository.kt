package com.example.movies.data

class MovieRepository {

    private val movies = listOf(
        Movie(1, "Inception", 2010, "Sci-Fi", "🌀", "A thief who steals secrets through dream-sharing technology is given a chance to have his criminal history erased."),
        Movie(2, "The Godfather", 1972, "Drama", "🎭", "The aging patriarch of an organized crime dynasty transfers control to his reluctant son."),
        Movie(3, "Titanic", 1997, "Romance", "🚢", "A young aristocrat falls in love with a poor artist aboard the ill-fated R.M.S. Titanic."),
        Movie(4, "The Dark Knight", 2008, "Action", "🦇", "Batman must accept one of the greatest tests to fight injustice when the Joker wreaks havoc on Gotham."),
        Movie(5, "Toy Story", 1995, "Animation", "🧸", "A cowboy doll feels threatened when a new spaceman action figure supplants him as top toy."),
        Movie(6, "Get Out", 2017, "Horror", "😱", "A young Black man visits his white girlfriend's family estate, where things are not what they seem."),
        Movie(7, "Interstellar", 2014, "Sci-Fi", "🚀", "A team of explorers travel through a wormhole in space to ensure humanity's survival."),
        Movie(8, "The Grand Budapest Hotel", 2014, "Comedy", "🎩", "A concierge and his lobby boy become involved in the theft of a priceless painting."),
        Movie(9, "Parasite", 2019, "Drama", "🏚️", "Greed and class discrimination threaten the newly formed symbiotic relationship between two families."),
        Movie(10, "Mad Max: Fury Road", 2015, "Action", "🔥", "In a post-apocalyptic wasteland, a woman rebels against a tyrant in search of her homeland."),
        Movie(11, "Spirited Away", 2001, "Animation", "🐉", "During her family's move, a sullen 10-year-old girl wanders into a world ruled by gods and spirits."),
        Movie(12, "La La Land", 2016, "Romance", "🎷", "A jazz pianist falls for an aspiring actress in Los Angeles while pursuing their dreams."),
        Movie(13, "Kazakh Khanate: Golden Throne", 2016, "Historical", "🏹", "Epic chronicle of the founding of the Kazakh Khanate and the struggles of its first khans."),
        Movie(14, "Kelin", 2009, "Drama", "🏔️", "A wordless tale of a young bride's life in the ancient Altai mountains, following Kazakh traditions."),
        Movie(15, "The Gift to Stalin", 2008, "Drama", "🚂", "A Kazakh railway worker shelters an orphaned boy deported during Stalin's repressions."),
        Movie(16, "Kelinka Sabina", 2014, "Comedy", "👰", "A young Kazakh bride navigates the comedic clash of modern life and old family traditions."),
        Movie(17, "Amanat", 2022, "Historical", "📜", "A sweeping drama about the poet Shakarim Kudaiberdiuly and his search for truth in turbulent times."),
        Movie(18, "Balqonyr", 2011, "Drama", "🚀", "Life and dreams intertwine near the Baikonur Cosmodrome in the vast Kazakh steppe."),
        Movie(19, "Sheker", 2023, "Drama", "🍬", "A poignant story of a Kazakh village girl chasing her dreams against the weight of tradition."),
        Movie(20, "The Wolf Land", 2004, "Adventure", "🐺", "A boy and his father survive the harsh beauty and danger of the Kazakh wilderness."),
        Movie(21, "Amre", 2022, "Biography", "🎤", "The rise of Amre Kashaubayev, the first Kazakh singer to perform on an international stage."),
        Movie(22, "Pulp Fiction", 1994, "Crime", "💼", "The lives of two mob hitmen, a boxer, and a pair of diner bandits intertwine in four tales of violence."),
        Movie(23, "Shal", 2012, "Drama", "🐺", "A Kazakh adaptation of 'The Old Man and the Sea', following an aging herder's battle against wolves on the steppe."),
        Movie(24, "The Hobbit: An Unexpected Journey", 2012, "Fantasy", "💍", "A reluctant hobbit sets out with thirteen dwarves to reclaim their mountain home from a dragon."),
        Movie(25, "Whiplash", 2014, "Drama", "🥁", "A young drummer enrolls at a cutthroat music conservatory under a ruthless instructor.")
    )

    fun getMovies(): List<Movie> = movies

    fun getGenres(): List<String> = listOf("All") + movies.map { it.genre }.distinct()

    fun getMovieById(id: Int): Movie? = movies.find { it.id == id }
}
