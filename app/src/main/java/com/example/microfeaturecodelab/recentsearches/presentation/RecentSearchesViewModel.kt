package com.example.microfeaturecodelab.recentsearches.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import com.example.microfeaturecodelab.base.MicroFeatureFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class RecentSearchesViewModel @AssistedInject constructor(
    @Assisted val coroutineScope: CoroutineScope
) : MicroFeatureViewModel {

    private val inputFlow = MutableStateFlow("1")

    // @TODO how to consume state flow in non compose scope renderer
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<RecentSearchesList> = inputFlow.flatMapLatest { input ->
        Log.d("RecentSearchesViewModel", "Fetching recent searches ${this.hashCode()}")
        delay(1000L)
        flowOf(
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
        ).catch {
            Log.d("RecentSearchesViewModel", "Failed to fetch recent searches")
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = RecentSearchesList(emptyList())
    )

    @AssistedFactory
    interface Factory : MicroFeatureFactory<RecentSearchesViewModel>

}

@Immutable
data class RecentSearchesList(
    val items: List<RecentSearchItem>
)

data class RecentSearchItem(
    val id: String,
    val positionName: String,
    val badge: String,
    val location: String,
    val jobType: String = "Full-time",
)
