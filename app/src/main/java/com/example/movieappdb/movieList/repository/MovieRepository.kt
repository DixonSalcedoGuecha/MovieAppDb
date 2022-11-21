package com.example.movieappdb.movieList.repository

import androidx.lifecycle.LiveData
import com.example.movieappdb.movieList.datasource.RestDataSource
import com.example.movieappdb.movieList.model.Movies
import com.example.movieappdb.movieList.model.MoviesDao
import javax.inject.Inject

interface MovieRepository {
    suspend fun getInsertMovies()
    fun updateItemMovie(movies: Movies)
    fun getAllMovies(): LiveData<List<Movies>>
    fun getAllFavorites(): LiveData<List<Movies>>
    fun getAllRanking(): LiveData<List<Movies>>
    fun getItemMovie(id: Int): LiveData<Movies>
    fun getSearch(title: String): LiveData<List<Movies>>

}

class MovieRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val moviesDao: MoviesDao
) : MovieRepository {
    override suspend fun getInsertMovies() {

        dataSource.getMovieList().results.map {
            moviesDao.insertMovie(
                Movies(
                    original_title = it.title,
                    image = "https://image.tmdb.org/t/p/original${it.image}",
                    overview = it.overview,
                    favorite = false, ranking = 0
                )
            )
        }
    }


    override fun updateItemMovie(movies: Movies) = moviesDao.updateItem(movies)
    override fun getAllMovies(): LiveData<List<Movies>> = moviesDao.getAll()
    override fun getAllFavorites(): LiveData<List<Movies>> = moviesDao.getFavorites(true)
    override fun getAllRanking(): LiveData<List<Movies>> = moviesDao.getRanking()

    override fun getItemMovie(id: Int): LiveData<Movies> = moviesDao.getItem(idItem = id)
    override fun getSearch(title: String): LiveData<List<Movies>> =
        moviesDao.getSearch(title = title)
}