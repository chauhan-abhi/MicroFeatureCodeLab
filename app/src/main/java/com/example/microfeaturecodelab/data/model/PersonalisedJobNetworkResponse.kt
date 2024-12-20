package com.example.microfeaturecodelab.data.model

import com.example.microfeaturecodelab.domain.model.JobRecommendation
import com.example.microfeaturecodelab.domain.model.JobRecommendations
import kotlinx.serialization.Serializable

@Serializable
data class PersonalisedJobNetworkResponse(
    val header: String,
    val personalisedJobList: List<JobItemResponse>
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

fun PersonalisedJobNetworkResponse.toDomain(): JobRecommendations {
    return JobRecommendations(
        sectionTitle = header,
        jobRecommendations = personalisedJobList.map { it.toDomain() }
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
