package com.example.movies

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.data.Movie
import com.example.movies.data.MovieRepository
import com.example.movies.ui.theme.MoviesTheme
import androidx.compose.foundation.layout.Column
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val TAG = "LifecycleDemo"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Activity onCreate - this@MainActivity = $this")
        enableEdgeToEdge()
        setContent {
            MoviesTheme {
                MoviesApp()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Activity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Activity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Activity onDestroy - isChangingConfigurations = $isChangingConfigurations")
    }
}

@Composable
fun MoviesApp() {
    val navController = rememberNavController()

    val repository = remember { MovieRepository() }

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            MovieListScreen(
                onMovieClick = { movie -> navController.navigate("detail/${movie.id}") }
            )
        }
        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            val movie = movieId?.let { repository.getMovieById(it) }
            if (movie != null) {
                DetailScreen(movie = movie, onBackClick = { navController.popBackStack() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    onMovieClick: (Movie) -> Unit,
    viewModel: MovieListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Movies") })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.genres) { genre ->
                    FilterChip(
                        selected = genre == uiState.selectedGenre,
                        onClick = { viewModel.onGenreSelected(genre) },
                        label = { Text(genre) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.movies) { movie ->
                    MovieCard(movie = movie, onClick = { onMovieClick(movie) })
                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = movie.emoji, fontSize = 32.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "${movie.year} • ${movie.genre}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movie: Movie, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(movie.title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text("←", fontSize = 24.sp)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            Text(text = movie.emoji, fontSize = 96.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
            Text(
                text = "${movie.year} • ${movie.genre}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = movie.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onBackClick) {
                Text("Back to list")
            }
        }
    }
}
