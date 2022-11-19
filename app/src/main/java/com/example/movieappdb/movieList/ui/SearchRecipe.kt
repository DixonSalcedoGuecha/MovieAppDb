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
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieappdb.R
import coil.compose.AsyncImage
import com.example.movieappdb.movieList.model.Movies
import com.example.movieappdb.movieList.model.Routes


@Composable
fun SearchScreen(
    navigationController: NavHostController,
    viewModel: MovieListViewModel = hiltViewModel(),

    ) {


    MyAppSearch(viewModel, navigationController)

}

@Composable
fun MyAppSearch(
    viewModel: MovieListViewModel,
    navigationController: NavHostController,

    ) {

    val titleSearch: String by viewModel.title.observeAsState("")
    val movies by viewModel.listSearch.observeAsState(mutableListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)


    //val onChangeScreen by viewModel.onChangedScreen("").obse
    val movie = Movies(0, "",  "","",false)
    val movieUpdate by remember { mutableStateOf(movie) }

    Scaffold(


        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = {
                        navigationController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }

                    SearchInput(titleSearch) {

                        viewModel.titleSearch(it)

                    }
                },
                actions = {

                }
            )
        }
    ) {
        LazyColumn {

            var itemCount = movies.size
            if (isLoading) itemCount++

            items(count = itemCount) { index ->
                var auxIndex = index;
                if (isLoading) {
                    if (auxIndex == 0)
                        return@items LoadingCardSearch()
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
                                model = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png",
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

                                if (movie.id!=0){
                                    movieUpdate.id = movie.id
                                    movieUpdate.original_title = movie.original_title
                                    movieUpdate.favorite = !movie.favorite
                                    viewModel.updateFavorites(movieUpdate)
                                }

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
fun SearchInput(title: String, onTextChange: (String) -> Unit) {
    TextField(
        value = title,
        onValueChange = { onTextChange(it) },
        label = {
            Text(text = "Search")
        },
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(1.dp)
            .background(color = MaterialTheme.colors.surface),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0XFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA)
        )
    )

}


@Composable
fun LoadingCardSearch() {
    Card(

        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {


            SpacerSearch()
            Column {
                SpacerSearch()
                Box {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        SpacerSearch()
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
fun SpacerSearch(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))
