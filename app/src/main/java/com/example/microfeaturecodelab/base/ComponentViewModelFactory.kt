package com.example.microfeaturecodelab.base

import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentDependencies
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ComponentViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<String, @JvmSuppressWildcards MicroFeatureFactory<out MicroFeatureViewModel>>,
    private val composerFactories: Map<String, @JvmSuppressWildcards AbstractMicroFeatureComposer<out MicroFeatureViewModel>>,
) {

    fun create(
        componentConfig: ComponentConfig,
        viewModelScope: CoroutineScope
    ): ComponentDependencies {
        return viewModelFactories[componentConfig.type]?.let {
            val componentVM = viewModelFactories[componentConfig.type]?.create(viewModelScope)
            val componentComposer = composerFactories[componentConfig.type]
            if (componentVM != null && componentComposer != null) {
                ComponentDependencies(componentVM, componentComposer)
            } else {
                throw IllegalArgumentException("Cannot resolve component dependency: ${componentConfig.type}")
            }
        } ?: kotlin.run {
            throw IllegalArgumentException("Unknown component type: ${componentConfig.type}")
        }
    }
}