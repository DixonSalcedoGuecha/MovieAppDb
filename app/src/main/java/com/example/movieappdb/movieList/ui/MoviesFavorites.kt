package com.example.movieappdb.movieList.ui

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.pointer.pointerInteropFilter
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


    val movie = Movies(0, "", "", "", false, 0)
    val recipeUpdate by remember { mutableStateOf(movie) }



    MyAppFavorite(
        onUpdate = {
            viewModel.updateFavorites(recipeUpdate)
        }, recipeUpdate, moviesFavorite, isLoading, navigationController
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyAppFavorite(

    onUpdate: (Movies) -> Unit? = null!!,
    movieUpdate: Movies,
    moviesFavorite: List<Movies>,
    isLoading: Boolean,
    navigationController: NavHostController,
    viewModel: MovieListViewModel = hiltViewModel()

) {
    Scaffold(
        topBar = {
            TopAppBar(
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
                println(movie.ranking)



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
                                val rating = movie.ranking



                                Text(movie.original_title)

                                var ratingState by remember {
                                    mutableStateOf(rating)
                                }
                                var movieState by remember {
                                    mutableStateOf(movie.ranking)
                                }

                                var selected by remember {
                                    mutableStateOf(false)
                                }
                                val size by animateDpAsState(
                                    targetValue = if (selected) 32.dp else 24.dp,
                                    spring(Spring.DampingRatioMediumBouncy)
                                )

                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    for (i in 1..5) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.star_foreground),
                                            contentDescription = "star",
                                            modifier = Modifier
                                                .width(size)
                                                .height(size)
                                                .pointerInteropFilter {

                                                    when (it.action) {

                                                        MotionEvent.ACTION_DOWN -> {
                                                            selected = true
                                                            ratingState = i
                                                            movieState = i

                                                        }
                                                        MotionEvent.ACTION_UP -> {
                                                            selected = false
                                                        }
                                                    }

                                                    movieUpdate.id = movie.id
                                                    movieUpdate.original_title =
                                                        movie.original_title
                                                    movieUpdate.image = movie.image
                                                    movieUpdate.overview = movie.overview
                                                    movieUpdate.favorite = movie.favorite

                                                    movieUpdate.ranking = i

                                                    onUpdate.invoke(movieUpdate)

                                                    true
                                                },
                                            tint = if (i <= ratingState) Color(0xFFFFD700) else Color(
                                                0xFFA2ADB1
                                            )
                                        )
                                    }
                                }


                            }
                            Spacer()
                            IconButton(onClick = {


                                movieUpdate.id = movie.id
                                movieUpdate.image = movie.image
                                movieUpdate.original_title = movie.original_title
                                movieUpdate.overview = movie.overview
                                movieUpdate.favorite = !movie.favorite
                                movieUpdate.ranking = movie.ranking
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
