package com.example.microfeaturecodelab.recentsearches.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
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

    // Replace this later with use case backed data
    val uiState = mutableStateOf(
        RecentSearchesList(
            listOf(
                RecentSearchItem("rc1", "Android Engineer", "69 new", "San Francisco, CA"),
                RecentSearchItem("rc2", "Software Engineer", "", "India"),
                RecentSearchItem("rc3", "Staff Engineer", "", "India : Remote"),
            )
        )
    )

    // @TODO how to consume state flow in non compose scope renderer
//    @OptIn(ExperimentalCoroutinesApi::class)
//    val uiState: StateFlow<RecentSearchesList> = inputFlow.flatMapLatest { input ->
//        Log.d("RecentSearchesViewModel", "Fetching recent searches ${this.hashCode()}")
//        delay(1000L)
//        flowOf(
//            RecentSearchesList(
//                listOf(
//                    RecentSearchItem("rc1", "Android Engineer", "69 new", "San Francisco, CA"),
//                    RecentSearchItem("rc2", "Software Engineer", "", "India"),
//                    RecentSearchItem("rc3", "Staff Engineer", "", "India : Remote"),
//                )
//            )
//        ).catch {
//            Log.d("RecentSearchesViewModel", "Failed to fetch recent searches")
//        }
//    }.stateIn(
//        scope = coroutineScope,
//        started = SharingStarted.WhileSubscribed(5000L),
//        initialValue = RecentSearchesList(emptyList())
//    )

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
