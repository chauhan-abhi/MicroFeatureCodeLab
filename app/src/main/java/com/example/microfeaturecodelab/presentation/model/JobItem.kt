package com.example.microfeaturecodelab.presentation.model

import androidx.compose.runtime.Stable
import com.example.microfeaturecodelab.domain.model.JobRecommendation
import com.example.microfeaturecodelab.domain.model.JobRecommendations

@Stable
data class RecommendedJobSection(
    val sectionTitle: String,
    val items: List<JobItem>
)

@Stable
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

internal fun fakeJob() = RecommendedJobSection(
    sectionTitle = "Recommended Jobs",
    items = listOf(2).map {
        JobItem(
            id = it.toString(),
            jobTitle = "Software Engineer",
            companyName = "Google",
            location = "Mountain View, CA",
            description = "Work on the world's most advanced search engine.",
            companyImageUrl = "https://www.gstatic.com"
        )
    }
)


