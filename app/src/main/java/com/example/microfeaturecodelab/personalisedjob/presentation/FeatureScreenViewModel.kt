package com.example.microfeaturecodelab.personalisedjob.presentation

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeaturecodelab.base.ComponentViewModelFactory
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentDependencies
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.FeatureConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureScreenViewModel @Inject constructor(
    private val featureConfig: FeatureConfig,
    private val componentViewModelFactory: ComponentViewModelFactory,
) : ViewModel() {

    private lateinit var _components: List<ComponentConfig>

    private val _componentDependencyMap = mutableMapOf<String, ComponentDependencies>()
    val componentDependencyMap: Map<String, ComponentDependencies> get() = _componentDependencyMap

    init {
        Log.d("JobsViewModel", "JobsViewModel created")
        initialiseComponents()
    }

    private fun initialiseComponents() {
        viewModelScope.launch {
            _components = featureConfig.getComponentsConfig()
            _components.forEach { componentConfig ->
                val componentDependency =
                    componentViewModelFactory.create(componentConfig, viewModelScope)
                _componentDependencyMap[componentConfig.id] = componentDependency
            }
        }
    }

    fun getComponents() = Widgets(_components)
}

@Stable
data class Widgets(
    val items: List<ComponentConfig>,
)