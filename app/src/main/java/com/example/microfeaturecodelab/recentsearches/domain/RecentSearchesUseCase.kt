package com.example.microfeaturecodelab.recentsearches.domain

import com.example.microfeaturecodelab.recentsearches.presentation.RecentSearchItem
import com.example.microfeaturecodelab.recentsearches.presentation.RecentSearchesList
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RecentSearchesUseCase @Inject constructor() {

    // Fetch recent searches
    fun getRecentSearches(param: RecentSearchParameter) = flowOf(
        RecentSearchesList(
            listOf(
                RecentSearchItem("rc1", "Android Engineer", "69 new", "San Francisco, CA"),
                RecentSearchItem("rc2", "Software Engineer", "45 new", "India"),
                RecentSearchItem("rc3", "Staff Engineer", "21 new", "India : Remote"),
                RecentSearchItem("rc4", "Product Manager", "10 new", "London, UK"),
                RecentSearchItem("rc5", "Data Scientist", "", "Berlin, Germany"),
                RecentSearchItem("rc6", "Backend Engineer", "15 new", "Toronto, Canada"),
                RecentSearchItem("rc7", "Frontend Engineer", "12 new", "Austin, TX"),
                RecentSearchItem("rc8", "DevOps Engineer", "33 new", "Sydney, Australia"),
                RecentSearchItem("rc9", "Machine Learning Engineer", "", "New York, NY"),
                RecentSearchItem("rc10", "UI/UX Designer", "18 new", "Los Angeles, CA"),
                RecentSearchItem("rc11", "Full Stack Developer", "20 new", "Paris, France"),
                RecentSearchItem("rc12", "Cloud Architect", "", "Dublin, Ireland"),
                RecentSearchItem("rc13", "QA Engineer", "5 new", "Singapore"),
                RecentSearchItem("rc14", "Security Analyst", "", "Tokyo, Japan"),
                RecentSearchItem("rc15", "Technical Writer", "8 new", "Remote")
            )
        )
    )

}

data class RecentSearchParameter(
    val pageSize: Int
) {
    companion object {
        val DEFAULT = RecentSearchParameter(pageSize = 15)
    }
}