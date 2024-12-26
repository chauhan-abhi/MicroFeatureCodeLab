package com.example.microfeaturecodelab.presentation.uimodel

import android.util.Log
import com.example.microfeaturecodelab.base.MicroFeatureFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.domain.JobQueryParameter
import com.example.microfeaturecodelab.domain.GetJobsUseCase
import com.example.microfeaturecodelab.presentation.model.RecommendedJobSection
import com.example.microfeaturecodelab.presentation.model.toUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class PersonalisedJobViewModel @AssistedInject constructor(
    private val useCase: GetJobsUseCase,
    @Assisted val coroutineScope: CoroutineScope
): MicroFeatureViewModel {
    private val inputFlow = MutableStateFlow(JobQueryParameter.DEFAULT)

    val uiState: StateFlow<UiState> = inputFlow.flatMapLatest { input ->
        Log.d("PersonalisedJobViewModel", "Fetching jobs with input: ${this.hashCode()}")
        useCase.fetch(input).map {
            UiState.Success(it.toUiModel())
        }.catch {
            UiState.Error("Failed to fetch jobs")
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = UiState.Loading
    )

    val uiStateWorking: StateFlow<RecommendedJobSection> = inputFlow.flatMapLatest { input ->
        Log.d("PersonalisedJobViewModel", "Fetching jobs with input: ${this.hashCode()}")
        useCase.fetch(input).map {
            it.toUiModel()
        }.catch {
            Log.d("PersonalisedJobViewModel", "Failed to fetch jobs")
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = RecommendedJobSection("Loading", emptyList())
    )

    fun onEvent(event: Event) {
        when (event) {
            is Event.UpdateInput -> {
                coroutineScope.launch {
                    inputFlow.update {
                        event.newInput
                    }
                }
            }

            is Event.ItemClicked -> {
                // Handle item click
            }

            is Event.RetryClicked -> {
                // Handle retry click
            }

            is Event.ShowMore -> {
                // Handle show more
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val jobSection: RecommendedJobSection) : UiState()
        data class Error(val message: String) : UiState()
    }

    sealed interface Event {
        data class UpdateInput(val newInput: JobQueryParameter) : Event
        object ItemClicked : Event
        object RetryClicked : Event
        object ShowMore : Event
    }

    @AssistedFactory
    interface Factory: MicroFeatureFactory<PersonalisedJobViewModel>

}