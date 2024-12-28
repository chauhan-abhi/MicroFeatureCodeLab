package com.example.microfeaturecodelab.personalisedjob.data.model

import com.example.microfeaturecodelab.personalisedjob.domain.model.JobRecommendation
import com.example.microfeaturecodelab.personalisedjob.domain.model.JobRecommendations
import kotlinx.serialization.Serializable

@Serializable
data class JobNetworkResponse(
    val header: String,
    val jobList: List<JobItemResponse>
)

@Serializable
data class JobItemResponse(
    val id: String,
    val title: String,
    val company: String,
    val location: String,
    val description: String,
    val imageUrl: String
)

fun JobNetworkResponse.toDomain(): JobRecommendations {
    return JobRecommendations(
        sectionTitle = header,
        jobRecommendations = jobList.map { it.toDomain() }
    )
}

fun JobItemResponse.toDomain(): JobRecommendation {
    return JobRecommendation(
        id = id,
        jobTitle = title,
        companyName = company,
        location = location,
        description = description,
        companyImageUrl = imageUrl
    )
}
