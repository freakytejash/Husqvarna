package com.nmbs.husqvarna.api.retrofit

import com.nmbs.husqvarna.api.MoviesApiClient
import com.nmbs.husqvarna.api.responses.ConfigurationResponse
import com.nmbs.husqvarna.api.responses.MovieResponse
import com.nmbs.husqvarna.api.responses.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbClient : MoviesApiClient {

    @GET("configuration")
    override suspend fun getApiConfiguration(): Result<ConfigurationResponse>

    @GET("movie/popular")
    override suspend fun getPopularMovies(@Query("page") page: Int): Result<PopularMoviesResponse>

    @GET("movie/{id}")
    override suspend fun getMovieDetails(@Path("id") id: Int): Result<MovieResponse>
}
