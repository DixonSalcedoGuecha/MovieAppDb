package com.example.movieappdb.movieList.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "movies")
data class Movies(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "original_title") var original_title: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name= "overview") var overview: String,
    @ColumnInfo(name = "favorite") var favorite: Boolean,
    @ColumnInfo(name = "ranking") var ranking: Int
)

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: Movies)

    @Query("SELECT * FROM movies ORDER BY id DESC")
    fun getAll(): LiveData<List<Movies>>

    @Query("SELECT * FROM movies WHERE id = :idItem")
    fun getItem(idItem: Int): LiveData<Movies>

    @Update
    fun updateItem(movies: Movies)

    @Query("SELECT * FROM movies WHERE favorite = :favorite")
    fun getFavorites(favorite: Boolean): LiveData<List<Movies>>

    @Query("SELECT * FROM movies WHERE ranking > 0 ORDER BY ranking DESC")
    fun getRanking(): LiveData<List<Movies>>

    @Query("SELECT * FROM movies WHERE original_title LIKE '%'||:title||'%'")
    fun getSearch(title: String): LiveData<List<Movies>>

}