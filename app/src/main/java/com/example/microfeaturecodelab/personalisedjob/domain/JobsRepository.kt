package com.example.microfeaturecodelab.personalisedjob.domain

import com.example.microfeaturecodelab.personalisedjob.domain.model.JobRecommendations
import kotlinx.coroutines.flow.Flow

interface JobsRepository {

    fun fetchJobs(input: JobQueryParameter): Flow<JobRecommendations>
}

