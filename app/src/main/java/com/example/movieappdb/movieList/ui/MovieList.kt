package com.example.movieappdb.movieList.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieappdb.R
import com.example.movieappdb.movieList.domain.GetMoviesService
import com.example.movieappdb.movieList.model.Movies
import com.example.movieappdb.movieList.model.Routes
import com.example.movieappdb.ui.theme.MovieAppDbTheme

@Composable
fun MyApp1(
    navigationController: NavHostController,
    viewModel: MovieListViewModel = hiltViewModel(),

    ) {
    val movies by viewModel.movie.observeAsState(arrayListOf())
    val moviesFavorites by viewModel.movieFavorites.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val insertMovie by viewModel.isLoading.observeAsState(false)


    //val onChangeScreen by viewModel.onChangedScreen("").obse
    val movie = Movies(0, "", "", "", false)
    val moviesUpdate by remember { mutableStateOf(movie) }

    MyApp(
        onInsertAll = {
            viewModel.addAllMovies()
        }, onUpdate = {
            viewModel.updateFavorites(moviesUpdate)
        }, moviesUpdate, movies, isLoading, insertMovie, navigationController
    )


}


@Composable
fun MyApp(
    onInsertAll: (() -> Unit)? = null,
    onUpdate: (Movies) -> Unit? = null!!,
    movieUpdate: Movies,
    movies: List<Movies>,
    isLoading: Boolean,
    insertMovie: Boolean,
    navigationController: NavHostController,

    ) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "List of Movies")

                },
                actions = {
                    IconButton(onClick = {
                        navigationController.navigate(Routes.Search.rute)
                    }) {
                        Icon(Icons.Filled.Search, "Search")
                    }
                    IconButton(onClick = {
                        navigationController.navigate(Routes.Favorites.rute)
                    }) {
                        Icon(Icons.Filled.Favorite, "Favorite")
                    }
                }
            )
        }
    ) {
        LazyColumn {

            var itemCount = movies.size
            if (itemCount == 0 && insertMovie == false) {
                onInsertAll?.invoke()

            }
            if (isLoading) itemCount++

            items(count = itemCount) { index ->
                var auxIndex = index;
                if (isLoading) {
                    if (auxIndex == 0)
                        return@items LoadingCard()
                    auxIndex--
                }
                val movie = movies[auxIndex]



                Surface(modifier = Modifier.clickable {
                    navigationController.navigate(Routes.DetailMovie.createRoute(movie.id))
                }) {


                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = 1.dp,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            AsyncImage(
                                modifier = Modifier.size(50.dp),
                                model = movie.image,
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight
                            )
                            Spacer()
                            Column(
                                Modifier.weight(0.5f),
                            ) {


                                    Text(movie.original_title)
                                    //Text(text = "Dixon aca esta bien?")


                            }


                            Spacer()
                            IconButton(onClick = {

                                movieUpdate.id = movie.id
                                movieUpdate.original_title = movie.original_title
                                movieUpdate.image = movie.image
                                movieUpdate.overview = movie.overview
                                movieUpdate.favorite = !movie.favorite
                                onUpdate.invoke(movieUpdate)


                            }) {
                                if (movie.favorite) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_favorite_selected),
                                        contentDescription = null
                                    )

                                } else {
                                    Icon(Icons.Filled.FavoriteBorder, "Favorite")
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LoadingCard() {
    Card(

        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {


            Spacer()
            Column {
                Spacer()
                Box {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        Spacer()
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                    }
                }
            }

        }
    }
}


@Composable
fun Spacer(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppDbTheme {

    }
}