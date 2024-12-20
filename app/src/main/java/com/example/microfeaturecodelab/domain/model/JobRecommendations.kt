package com.example.microfeaturecodelab.domain.model

data class JobRecommendations(
    val sectionTitle: String,
    val jobRecommendations: List<JobRecommendation>
)

data class JobRecommendation(
    val id: String,
    val jobTitle: String,
    val companyName: String,
    val location: String,
    val description: String,
    val companyImageUrl: String
)