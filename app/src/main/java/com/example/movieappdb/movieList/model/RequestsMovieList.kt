package com.example.movieappdb.movieList.model

import com.google.gson.annotations.SerializedName

/*data class RequestsMovieList(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val overview: String,
    val original_title: String,
    val original_language: String,
    val popularity: Int,

    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int

)*/

data class RequestsMovieList(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val image: String,
    @SerializedName("overview") val overview: String
)
