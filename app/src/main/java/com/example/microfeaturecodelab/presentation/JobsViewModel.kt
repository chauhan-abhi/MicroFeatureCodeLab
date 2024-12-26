package com.example.microfeaturecodelab.presentation

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeaturecodelab.base.ComponentViewModelFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.presentation.featureconfig.ComponentConfig
import com.example.microfeaturecodelab.presentation.featureconfig.FeatureConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val featureConfig: FeatureConfig,
    private val componentViewModelFactory: ComponentViewModelFactory,
) : ViewModel() {

    private lateinit var _components: List<ComponentConfig>
    private val _componentViewModels = mutableMapOf<String, MicroFeatureViewModel>()
    val componentViewModels: Map<String, MicroFeatureViewModel> get() = _componentViewModels

    init {
        Log.d("JobsViewModel", "JobsViewModel created")
        initialiseComponents()
    }

    private fun initialiseComponents() {
        viewModelScope.launch {
            _components = featureConfig.getComponentsConfig()
            _components.forEach { componentConfig ->
                val viewModel = componentViewModelFactory.create(componentConfig, viewModelScope)
                _componentViewModels[componentConfig.id] = viewModel
            }
        }
    }

    fun getComponents() = Widgets(_components)
}

@Stable
data class Widgets(
    val items: List<ComponentConfig>,
)