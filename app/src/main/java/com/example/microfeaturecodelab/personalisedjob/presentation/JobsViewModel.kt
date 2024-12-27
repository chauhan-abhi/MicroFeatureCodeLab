package com.example.microfeaturecodelab.personalisedjob.presentation

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.base.ComponentViewModelFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.FeatureConfig
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

    private val _componentComposers = mutableMapOf<String, AbstractMicroFeatureComposer<out MicroFeatureViewModel>>()
    val componentComposers: Map<String, AbstractMicroFeatureComposer<out MicroFeatureViewModel>> get() = _componentComposers

    init {
        Log.d("JobsViewModel", "JobsViewModel created")
        initialiseComponents()
    }

    private fun initialiseComponents() {
        viewModelScope.launch {
            _components = featureConfig.getComponentsConfig()
            _components.forEach { componentConfig ->
                val (componentVM, componentComposer) = componentViewModelFactory.create(componentConfig, viewModelScope)
                componentVM?.let { _componentViewModels[componentConfig.id] = componentVM }
                componentComposer?.let { _componentComposers[componentConfig.id] = componentComposer }
            }
        }
    }

    fun getComponents() = Widgets(_components)
}

@Stable
data class Widgets(
    val items: List<ComponentConfig>,
)