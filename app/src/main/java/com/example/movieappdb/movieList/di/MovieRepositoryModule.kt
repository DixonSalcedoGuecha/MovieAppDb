package com.example.movieappdb.movieList.di

import com.example.movieappdb.movieList.repository.MovieRepository
import com.example.movieappdb.movieList.repository.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieRepositoryModule {

    @Binds
    @Singleton
    abstract fun movieRepository(repo: MovieRepositoryImp): MovieRepository
}