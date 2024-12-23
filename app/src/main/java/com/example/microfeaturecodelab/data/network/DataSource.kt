package com.example.microfeaturecodelab.data.network

import JvmUnitTestDemoAssetManager
import com.example.microfeaturecodelab.data.ApiParameters
import com.example.microfeaturecodelab.data.model.JobNetworkResponse
import com.example.microfeaturecodelab.di.AppDispatchers
import com.example.microfeaturecodelab.di.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

interface DataSource {

    suspend fun fetchRecommendedJobs(apiParameters: ApiParameters): JobNetworkResponse

    suspend fun fetchJobs(apiParameters: ApiParameters): Unit
}

@OptIn(ExperimentalSerializationApi::class)
class LocalDataSourceImpl @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: DemoAssetManager = JvmUnitTestDemoAssetManager,
) : DataSource {

    override suspend fun fetchRecommendedJobs(apiParameters: ApiParameters): JobNetworkResponse {
        // This is where you would make a network request to fetch the recommended jobs
        return withContext(ioDispatcher) {
            assets.open(RECOMMENDED_JOBS_ASSET).use(networkJson::decodeFromStream)
        }
    }

    override suspend fun fetchJobs(apiParameters: ApiParameters) {
        // This is where you would make a network request to fetch the jobs

    }

    companion object {
        private const val RECOMMENDED_JOBS_ASSET = "recommendedjobs.json"
    }
}