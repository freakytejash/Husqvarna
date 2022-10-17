package com.nmbs.husqvarna.data.movies

import com.nmbs.husqvarna.model.MovieSummary
import com.nmbs.husqvarna.model.MovieDetails

interface MoviesRepository {
    suspend fun fetchPopularMovies(page: Int): Result<List<MovieSummary>>

    suspend fun fetchMovieDetails(id: Int): Result<MovieDetails>
}
