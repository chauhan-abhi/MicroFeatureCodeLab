package com.example.microfeaturecodelab.data.network

import com.example.microfeaturecodelab.data.model.PersonalisedJobNetworkResponse
import com.example.microfeaturecodelab.di.AppDispatchers
import com.example.microfeaturecodelab.di.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface NetworkDataSource {

    suspend fun fetchRecommendedJobs(): PersonalisedJobNetworkResponse
}

class NetworkDataSourceImpl @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
): NetworkDataSource {
    override suspend fun fetchRecommendedJobs(): PersonalisedJobNetworkResponse {
        TODO("Not yet implemented")
        // This is where you would make a network request to fetch the recommended jobs
    }
}