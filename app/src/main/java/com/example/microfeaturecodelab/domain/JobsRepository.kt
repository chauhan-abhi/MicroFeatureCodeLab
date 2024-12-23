package com.example.microfeaturecodelab.domain

import com.example.microfeaturecodelab.domain.model.JobRecommendations
import kotlinx.coroutines.flow.Flow

interface JobsRepository {

    fun fetchJobs(input: JobQueryParameter): Flow<JobRecommendations>
}

