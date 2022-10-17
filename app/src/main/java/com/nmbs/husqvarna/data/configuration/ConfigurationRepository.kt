package com.nmbs.husqvarna.data.configuration

import com.nmbs.husqvarna.model.ApiConfiguration

interface ConfigurationRepository {
    suspend fun fetchConfiguration(): Result<ApiConfiguration>
}
