package com.example.movieappdb.movieList.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappdb.movieList.model.Movies
import com.example.movieappdb.movieList.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsMovieViewModel @Inject constructor(
    private val userRepo: MovieRepository
) : ViewModel() {
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }


    val isLoading: LiveData<Boolean> get() = _isLoading



    fun getItemRecipe(id: Int) : LiveData<Movies> {
        return   userRepo.getItemMovie(id)
    }

}