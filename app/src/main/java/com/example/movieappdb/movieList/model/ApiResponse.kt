package com.example.movieappdb.movieList.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") var results: List<RequestsMovieList>,
    @SerializedName("total_pages") val total_pages: String,
    @SerializedName("total_results") val total_results: String
)