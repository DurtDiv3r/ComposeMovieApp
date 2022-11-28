package com.islaharper.moviecompose.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.islaharper.moviecompose.model.Movie
import com.islaharper.moviecompose.model.getMovies
import com.islaharper.moviecompose.widgets.MovieRow

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {

    val movieList = getMovies().filter { movie ->
        movie.id == movieId
    }

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.LightGray,
            elevation = 10.dp
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Details", style = MaterialTheme.typography.h5)
        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(movie = movieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Images:")

                HorizontalImageScrollable(movieList)

//                Spacer(modifier = Modifier.height(23.dp))
//                Button(onClick = {
//                    navController.popBackStack()
//                }) {
//                    Text(text = "Back...")
//                }
            }
        }

    }
}

@Composable
private fun HorizontalImageScrollable(movieList: List<Movie>) {
    LazyRow {
        items(movieList.first().images) { image ->
            Card(
                modifier = Modifier
                    .padding(6.dp)
                    .size(250.dp),
                elevation = 5.dp
            ) {
                //COIL implementation
                AsyncImage(model = image, contentDescription = "Movie Image")
            }

        }
    }
}