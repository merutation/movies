package com.example.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movies.data.Movie
import com.example.movies.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "LifecycleDemo"

data class MovieListUiState(
    val movies: List<Movie> = emptyList(),
    val genres: List<String> = emptyList(),
    val selectedGenre: String = "All"
)

class MovieListViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _uiState = MutableStateFlow(MovieListUiState())

    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    init {
        Log.d(TAG, "ViewModel init - this@MovieListViewModel = $this")
        _uiState.value = MovieListUiState(
            movies = repository.getMovies(),
            genres = repository.getGenres(),
            selectedGenre = "All"
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel onCleared - this@MovieListViewModel = $this")
    }

    fun onGenreSelected(genre: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedGenre = genre,
                movies = filterByGenre(genre)
            )
        }
    }

    private fun filterByGenre(genre: String): List<Movie> {
        val allMovies = repository.getMovies()
        return if (genre == "All") allMovies else allMovies.filter { it.genre == genre }
    }
}
