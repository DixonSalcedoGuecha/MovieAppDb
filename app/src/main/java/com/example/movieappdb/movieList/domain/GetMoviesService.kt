package com.example.movieappdb.movieList.domain

import com.example.movieappdb.movieList.datasource.RestDataSource
import com.example.movieappdb.movieList.model.MoviesDao
import com.example.movieappdb.movieList.repository.MovieRepository
import javax.inject.Inject

class GetMoviesService @Inject constructor(
    private val userRepo: MovieRepository
) {

     fun getMovies(): Boolean {
         return userRepo.getAllMovies().value.isNullOrEmpty()
    }
}