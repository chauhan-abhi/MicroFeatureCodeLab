package com.example.microfeaturecodelab.domain

import com.example.microfeaturecodelab.domain.model.JobRecommendations
import kotlinx.coroutines.flow.Flow

interface PersonalisedJobRepository {

    fun fetchPersonalisedJobs(): Flow<JobRecommendations>
}

