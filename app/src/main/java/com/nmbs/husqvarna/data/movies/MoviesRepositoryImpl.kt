package com.nmbs.husqvarna.data.movies

import com.nmbs.husqvarna.data.configuration.ConfigurationRepository
import com.nmbs.husqvarna.model.ApiConfiguration
import com.nmbs.husqvarna.model.MovieDetails
import com.nmbs.husqvarna.model.MovieSummary
import kotlin.math.roundToInt

class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val configurationRepository: ConfigurationRepository,
) :
    MoviesRepository {

    override suspend fun fetchPopularMovies(page: Int) : Result<List<MovieSummary>> {
        return fetchWithConfiguration { apiConfiguration ->
            Result.success(
                moviesRemoteDataSource.getPopularMovies(page).getOrElse { error ->
                    return@fetchWithConfiguration Result.failure(error)
                }.popularMovies.map { movie ->
                    MovieSummary(
                        id = movie.id,
                        title = movie.title,
                        rating = movie.voteAverage,
                        releaseDate = movie.releaseDate,
                        imageUrl = apiConfiguration.urlForBackdrop(movie.backdropPath)
                    )
                }
            )
        }
    }

    override suspend fun fetchMovieDetails(id: Int): Result<MovieDetails> {
        return fetchWithConfiguration { apiConfiguration ->
            Result.success(
                moviesRemoteDataSource.getMovieDetails(id).getOrElse { error ->
                    return@fetchWithConfiguration Result.failure(error)
                }.let { details ->
                    MovieDetails(
                        id = details.id,
                        title = details.title,
                        runtime = details.runtime,
                        tagline = details.tagline,
                        overview = details.overview,
                        releaseDate = details.releaseDate,
                        voteAverage = (details.voteAverage * 10.0f).roundToInt() / 10.0f,
                        genres = details.genres.map { genre ->
                            genre.name
                        },
                        backdropUrl = apiConfiguration.urlForBackdrop(details.backdropPath),
                        posterUrl = apiConfiguration.urlForPoster(details.posterPath)
                    )
                }
            )
        }
    }

    private suspend fun <T> fetchWithConfiguration(
        onSuccessConfigurationFetch: suspend (ApiConfiguration) -> Result<T>
    ): Result<T> {
        val apiConfiguration = configurationRepository.fetchConfiguration().getOrElse { error ->
            return Result.failure(error)
        }

        return onSuccessConfigurationFetch(apiConfiguration)
    }
}
