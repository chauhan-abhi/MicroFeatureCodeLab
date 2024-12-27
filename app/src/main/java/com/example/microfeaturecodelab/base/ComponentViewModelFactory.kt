package com.example.microfeaturecodelab.base

import android.util.Log
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ComponentViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<String, @JvmSuppressWildcards MicroFeatureFactory<out MicroFeatureViewModel>>,
    private val composerFactories: Map<String, @JvmSuppressWildcards AbstractMicroFeatureComposer<out MicroFeatureViewModel>>,
) {

    fun create(
        componentConfig: ComponentConfig,
        viewModelScope: CoroutineScope
    ): Pair<MicroFeatureViewModel?, AbstractMicroFeatureComposer<out MicroFeatureViewModel>?> {
        return viewModelFactories[componentConfig.type]?.let {
            Log.d("ComponentViewModelFactory", "Creating viewmodel for ${componentConfig.type}")
            val componentVM = viewModelFactories[componentConfig.type]?.create(viewModelScope)
            val componentComposer = composerFactories[componentConfig.type]
            componentVM to componentComposer
        } ?: kotlin.run {
            throw IllegalArgumentException("Unknown component type: ${componentConfig.type}")
        }
    }
}