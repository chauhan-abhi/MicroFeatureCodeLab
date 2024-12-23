package com.example.microfeaturecodelab.presentation.model

import com.example.microfeaturecodelab.domain.model.JobRecommendation
import com.example.microfeaturecodelab.domain.model.JobRecommendations

data class RecommendedJobSection(
    val sectionTitle: String,
    val items: List<JobItem>
)

data class JobItem(
    val id: String,
    val jobTitle: String,
    val companyName: String,
    val location: String,
    val description: String,
    val companyImageUrl: String
)

fun JobRecommendations.toUiModel(): RecommendedJobSection {
    return RecommendedJobSection(
        sectionTitle = sectionTitle,
        items = jobRecommendations.map { it.toUiModel() }
    )
}

fun JobRecommendation.toUiModel(): JobItem {
    return JobItem(
        id = id,
        jobTitle = jobTitle,
        companyName = companyName,
        location = location,
        description = description,
        companyImageUrl = companyImageUrl
    )
}
