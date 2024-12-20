package com.example.microfeaturecodelab.data

import com.example.microfeaturecodelab.data.model.toDomain
import com.example.microfeaturecodelab.data.network.NetworkDataSource
import com.example.microfeaturecodelab.domain.PersonalisedJobRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonalisedJobRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource
) : PersonalisedJobRepository {
    override fun fetchPersonalisedJobs() = flow {
        emit(
            dataSource.fetchRecommendedJobs().toDomain()
        )
    }.catch { e ->
        // Handle exceptions here
    }
}