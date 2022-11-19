package com.example.movieappdb.movieList.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@Composable
fun DetailView(
    navigationController: NavHostController,
    id: Int,
    viewModel: DetailsMovieViewModel = hiltViewModel()
) {

    val movies by viewModel.getItemRecipe(id).observeAsState()
    println(id)
    //println(recipes?.image)
    println(movies?.original_title)
    val title = movies?.original_title

    //  val recipe = recipes[id]
    println(movies)

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(),

                title = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { navigationController.popBackStack() },
                        tint = Color.White
                    )
                    Text(text = "Detail of the recipe")

                }
            )
        }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center

            ) {

                AsyncImage(
                    model = movies?.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop

                )


            }
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary)
                    .padding(16.dp)
            ) {

                Text(
                    text = movies?.original_title.toString(),
                    style = MaterialTheme.typography.h6
                )
                Spacer()
            }
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(16.dp)
            ) {

                Text(text = movies?.overview.toString(),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.h6)

            }
        }
    }
}




