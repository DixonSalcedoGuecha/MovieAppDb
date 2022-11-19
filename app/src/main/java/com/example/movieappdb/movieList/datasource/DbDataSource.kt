package com.example.movieappdb.movieList.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieappdb.movieList.model.Movies
import com.example.movieappdb.movieList.model.MoviesDao

@Database(entities = [Movies::class], version = 1 )
abstract class DbDataSource: RoomDatabase(){

    abstract fun moviesDao(): MoviesDao
}