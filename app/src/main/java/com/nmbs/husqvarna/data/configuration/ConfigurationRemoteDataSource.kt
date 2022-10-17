package com.nmbs.husqvarna.data.configuration

import com.nmbs.husqvarna.api.MoviesApiClient
import com.nmbs.husqvarna.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConfigurationRemoteDataSource @Inject constructor(
    private val moviesApiClient: MoviesApiClient,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getApiConfiguration() = withContext(ioDispatcher) {
        moviesApiClient.getApiConfiguration()
    }
}
