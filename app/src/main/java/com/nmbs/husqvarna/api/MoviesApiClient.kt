package com.nmbs.husqvarna.api

import com.nmbs.husqvarna.api.responses.ConfigurationResponse
import com.nmbs.husqvarna.api.responses.MovieResponse
import com.nmbs.husqvarna.api.responses.PopularMoviesResponse

interface MoviesApiClient {
    suspend fun getApiConfiguration(): Result<ConfigurationResponse>

    suspend fun getPopularMovies(page: Int): Result<PopularMoviesResponse>

    suspend fun getMovieDetails(id: Int): Result<MovieResponse>
}
