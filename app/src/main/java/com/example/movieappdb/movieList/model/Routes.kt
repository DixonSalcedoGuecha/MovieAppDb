package com.example.movieappdb.movieList.model

sealed class Routes (val rute:String){
    object List: Routes("List")
    object DetailMovie: Routes("DetailMovie/{id}"){
        fun createRoute(id:Int) = "DetailMovie/$id"
    }
    object Favorites: Routes("Favorites")
    object Search: Routes("Search")
}