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
class MovieListViewModel @Inject constructor(
    private val userRepo: MovieRepository
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _insertMovie: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val insertMovie: LiveData<Boolean> get() = _insertMovie


    val movie: LiveData<List<Movies>> by lazy {
        userRepo.getAllMovies()
    }

    val movieFavorites: LiveData<List<Movies>> by lazy {
        userRepo.getAllFavorites()
    }
    private val _movieUpdate: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val movieUpdate: LiveData<String> get() = _movieUpdate

    fun addAllMovies() {


        if (_isLoading.value == false && _insertMovie.value == false)

            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                userRepo.getInsertMovies()
                _insertMovie.postValue(true)
                _isLoading.postValue(false)
            }
    }

    fun updateFavorites(movie: Movies) {
        println(movie)
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.updateItemMovie(movie)
        }
    }

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    val listSearch: LiveData<List<Movies>> by lazy {
        titleSearch(_title.value.toString())
    }

    fun titleSearch(title: String): LiveData<List<Movies>> {
        println(title)
        _title.value = title
        if (title.isNotEmpty()) {
            userRepo.getSearch(title)
        }
        return userRepo.getSearch(_title.value.toString())

    }


}