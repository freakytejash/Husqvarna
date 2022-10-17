package com.nmbs.husqvarna.di

import com.nmbs.husqvarna.data.configuration.ConfigurationRemoteDataSource
import com.nmbs.husqvarna.data.configuration.ConfigurationRepository
import com.nmbs.husqvarna.data.configuration.ConfigurationRepositoryImpl
import com.nmbs.husqvarna.data.movies.MoviesRemoteDataSource
import com.nmbs.husqvarna.data.movies.MoviesRepository
import com.nmbs.husqvarna.data.movies.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    fun provideMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        configurationRepository: ConfigurationRepository
    ): MoviesRepository =
        MoviesRepositoryImpl(moviesRemoteDataSource, configurationRepository)

    @Provides
    fun provideConfigurationRepository(
        configurationRemoteDataSource: ConfigurationRemoteDataSource
    ): ConfigurationRepository =
        ConfigurationRepositoryImpl(configurationRemoteDataSource)
}
