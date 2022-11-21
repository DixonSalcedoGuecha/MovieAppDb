package com.example.movieappdb.movieList.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappdb.movieList.model.Movies
import com.example.movieappdb.movieList.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesFavoritesViewModel@Inject constructor(
    private val userRepo: MovieRepository
) : ViewModel() {


    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }


    val isLoading: LiveData<Boolean> get() = _isLoading




    val movieFavorites: LiveData<List<Movies>> by lazy {
        userRepo.getAllFavorites()
    }








    fun updateFavorites(recipes: Movies) {
        println(recipes)
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.updateItemMovie(recipes)
        }
    }




    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title




}
