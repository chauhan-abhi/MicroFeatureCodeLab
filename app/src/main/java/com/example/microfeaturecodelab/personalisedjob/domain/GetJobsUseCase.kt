package com.example.microfeaturecodelab.personalisedjob.domain

import javax.inject.Inject

class GetJobsUseCase @Inject constructor(
    private val repository: JobsRepository
) {

    fun fetch(input: JobQueryParameter) = repository.fetchJobs(input)

}

data class JobQueryParameter(
    val pageSize: Int,
    val type: RecommendationType = RecommendationType.DEFAULT
) {
    companion object {
        val DEFAULT = JobQueryParameter(pageSize = 3)
    }
}

enum class RecommendationType {
    TOP_PICKS,
    TOP_APPLICANT,
    CURRENT_COMPANY,
    DEFAULT
}