package com.example.movieappdb.movieList.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieappdb.R
import com.example.movieappdb.movieList.model.Movies
import com.example.movieappdb.movieList.model.Routes

@Composable
fun MyFavorites(
    navigationController: NavHostController,
    viewModel: MoviesFavoritesViewModel = hiltViewModel(),

    ) {

    val moviesFavorite by viewModel.movieFavorites.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)



    val movie = Movies(0,"","","",false)
    val recipeUpdate by remember { mutableStateOf(movie) }



    MyAppFavorite(
        onUpdate = {
            viewModel.updateFavorites(recipeUpdate)
        }, recipeUpdate, moviesFavorite, isLoading, navigationController
    )

}

@Composable
fun MyAppFavorite(

    onUpdate: (Movies) -> Unit? = null!!,
    movieUpdate: Movies,
    moviesFavorite: List<Movies>,
    isLoading: Boolean,
    navigationController: NavHostController,

    ) {
    Scaffold(
        topBar = {
            TopAppBar  (
                title = {
                    IconButton(onClick = {
                        navigationController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "Search")
                    }
                    Text(text = "Favorites")

                }
            )
        }
    ) {
        LazyColumn {

            var itemCount = moviesFavorite.size
            if (isLoading) itemCount++

            items(count = itemCount) { index ->
                var auxIndex = index;
                if (isLoading) {
                    if (auxIndex == 0)
                        return@items LoadingCardFavorite()
                    auxIndex--
                }
                val movie = moviesFavorite[auxIndex]



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
                                Modifier.weight(1f),
                            ) {
                                Text(movie.original_title)
                            }
                            Spacer()
                            IconButton(onClick = {

                                movieUpdate.id = movie.id
                                movieUpdate.image = movie.image
                                movieUpdate.original_title = movie.original_title
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
fun LoadingCardFavorite() {
    Card(

        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {


            SpacerFavorite()
            Column {
                SpacerFavorite()
                Box {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        SpacerFavorite()
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
fun SpacerFavorite(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))
