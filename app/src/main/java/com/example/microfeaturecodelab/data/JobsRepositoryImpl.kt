package com.example.microfeaturecodelab.data

import com.example.microfeaturecodelab.data.model.toDomain
import com.example.microfeaturecodelab.data.network.DataSource
import com.example.microfeaturecodelab.domain.JobQueryParameter
import com.example.microfeaturecodelab.domain.JobsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JobsRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : JobsRepository {

    override fun fetchJobs(input: JobQueryParameter) = flow {
        emit(
            dataSource.fetchRecommendedJobs(
                ApiParameters(
                    pageSize = input.pageSize,
                    requestType = input.type.name
                )
            ).toDomain()
        )
    }.catch { e ->
        // Handle exceptions here
    }
}

data class ApiParameters(
    val pageSize: Int,
    val requestType: String = "Default"
)