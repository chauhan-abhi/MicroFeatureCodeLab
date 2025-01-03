package com.example.microfeaturecodelab.recentsearches.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import com.example.microfeaturecodelab.base.MicroFeatureFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.FetchStrategy
import com.example.microfeaturecodelab.recentsearches.domain.RecentSearchParameter
import com.example.microfeaturecodelab.recentsearches.domain.RecentSearchesUseCase
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
import kotlinx.coroutines.flow.stateIn

class RecentSearchesViewModel @AssistedInject constructor(
    private val useCase: RecentSearchesUseCase,
    @Assisted val coroutineScope: CoroutineScope,
    @Assisted val sharingStarted: SharingStarted
) : MicroFeatureViewModel {

    private val inputFlow = MutableStateFlow(RecentSearchParameter.DEFAULT)

    // @TODO how to consume state flow in non compose scope renderer
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<RecentSearchesList> = inputFlow.flatMapLatest { input ->
        Log.d("RecentSearchesViewModel", "Fetching recent searches ${this.hashCode()}")
        delay(5000L)
        useCase.getRecentSearches(input)
        .catch {
            Log.d("RecentSearchesViewModel", "Failed to fetch recent searches")
        }
    }.stateIn(
        scope = coroutineScope,
        started = sharingStarted,
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
