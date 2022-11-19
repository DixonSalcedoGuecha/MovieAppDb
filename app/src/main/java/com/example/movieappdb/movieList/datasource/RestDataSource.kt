package com.example.movieappdb.movieList.datasource

import com.example.movieappdb.movieList.domain.Constants
import com.example.movieappdb.movieList.domain.Constants.TOKEN
import com.example.movieappdb.movieList.model.ApiResponse
import retrofit2.http.GET

interface RestDataSource {

    @GET("movie?sort_by=popularity.desc&api_key=$TOKEN")
    suspend fun getMovieList(): ApiResponse

}